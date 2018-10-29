【Allen 原理： 参考本地java文件：BaseDaoJdbc，目前已经测试】

poi导出excel最常用的是第一种方式HSSFWorkbook，不过这种方式数据量大的话会产生内存溢出问题，SXSSFWorkbook是一种大数据量导出格式，csv是另一种excel导出的一种轻快的实现。
先介绍一下这三种格式的特点
1 HSSFWorkbook  excel文件底层是txt实现，我们经常见到的excel都是这种实现的。
2 SXSSFWorkbook  excel文件底层是xml实现，同样的数据量，大约是第一种的1/6-1/4之间
3 csv 这个比较新潮，数据量应该更小，可以百度。



（页面最下方SXSSF (Since POI 3.8 beta3）
从3.8-beta3版本开始，POI提供了一个低内存的SXSSF API构建在XSSF之上。
SXSSF是XSSF的API兼容的流扩展，当必须产生非常大的sheet表格，并且堆空间有限时使用。 
XSSF可以访问文档中的所有行；而SXSSF通过限制对滑动窗口中的行的访问来实现其低内存占用，不再位于滑动窗口中的旧行将无法访问，因为它们已写入磁盘。
在自动刷新模式下，可以指定访问窗口的大小（windowSize），以在内存中保存一定数量的行。当达到该值时，创建一个附加行将导致具有最低索引的行从访问窗口中删除并写入磁盘。或者，窗口大小可以设置为动态增长;它可以根据需要通过显式调用flushRows（int keepRows）定期修剪。
由于实现的流传输性质，与XSSF相比存在以下限制： 
<1>在一个时间点只能访问有限数量的行(取决于windowSize的大小)。 
<2>不支持Sheet.clone（）。 
<3>不支持公式计算


2.在输入的时候我查阅了poi的api发现一般是不能大批量数据输入的，但是poi的2007支持大数据处理
Workbook wb = new SXSSFWorkbook(5000);
在生成Workbook 时给工作簿一个内存数据存在条数，这样一旦这个Workbook 中数据量超过5000就会写入到磁盘中，减少内存的使用量来提高速度和避免溢出。

对于大型excel的创建且不会内存溢出的，就只有SXSSFWorkbook了。它的原理很简单，用硬盘空间换内存（就像hash map用空间换时间一样）。
【】【使用注意情况：【C:\Users\..\AppData\Local\Temp】】
SXSSFWorkbook是streaming版本的XSSFWorkbook,它只会保存最新的excel rows在内存里供查看，在此之前的excel rows都会被写入到硬盘里（Windows电脑的话，是写入到C盘根目录下的temp文件夹）。
被写入到硬盘里的rows是不可见的/不可访问的。只有还保存在内存里的才可以被访问到。
【临时文件的存储位置】
window环境本地缓存会存放在C盘用户下面AppDate\Local\Temp 下面
linux环境下会存放在 temp/poifile 录下
【临时文件的处理策略】
poi 3.8 中存在SXSSFWorkbook 但是没有despose() 方法，使用时间长了的话有可能造成磁盘没有空间，
方式1：手动清除临时文件
①POI3.8并没有提供方法来清除临时文件，为此，这里可以自己手动进行清除：
②方式2：将POI3.8替换成更高的版本，并利用dispose方法清除临时文件http://poi.apache.org/apidocs/org/apache/poi/xssf/streaming/SXSSFWorkbook.html

【】【】【】【】【】【】【】【】【其他注意事项】【】【】【】【】【】【】【】【】【】
使用时注意一下几点： 
1、SXSSF是限制滑动窗口中的行的访问来实现低内存的占用，注意是限制的是访问； 
2、滑动窗口的默认大小windowSize为100，是由SXSSFWorkbook.DEFAULT_WINDOW_SIZE定义。 
3、可new一个新的SXSSFWorkbook（int windowSize）在工作簿构建时指定窗口大小 ，例如： 
SXSSFWorkbook wb = new SXSSFWorkbook(1000); 
此时wb的滑动窗口大小为1000； 
4、windowSize为-1时，表示可以无限制访问。此种情况下，所有未调用flushRows()刷新的记录都可用于随机访问； 
5、SXSSF中的数据达到滑动窗口的限制数量，会产生临时文件且不会自动删除（Win和Linux的默认路径不同），通过调用dispose方法即可删除临时文件： 
SXSSFWorkbook wb = new SXSSFWorkbook(100); 
//假装有许多操作 
wb.dispose(); 
6、使用createRow()创建新行且未刷新记录的总数超过指定的窗口大小时，具有最低索引值的行将被刷新，并且不能再通过getRow（）访问。 
比如窗口行数为100，内存当前有100行，createRow（）创建一个新行，索引值为0的那一行被刷新到本地文件,该行将无法访问，因为它们已写入磁盘了。（这一点解释了我上一篇由空白行引起的问题，传送门）


原文：https://blog.csdn.net/V_Axis/article/details/78271493 


【】【】【】【】【】【优化空间】【】【】】】【】【】【】【】
①、采用SpringBoot原始嵌入的TemplateJdbc测试下性能
②、在原始基础上进行多线程测试
③、目前此项目中，只有这个BaseDaoJdbc是大数据量导出

【异常：】
如果你从开始菜单中启动excel2007，默认是：1048576
 Invalid row number (1048576) outside allowable range (0..1048575)] with root cause

java.lang.IllegalArgumentException: Invalid row number (1048576) outside allowable range (0..1048575)
