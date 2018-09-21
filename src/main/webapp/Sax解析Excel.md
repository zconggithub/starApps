1.Excel文件XML结构
Excel2007是使用XML格式来存储的，把一个excel文件(test.xlsx)后缀改为.zip,打开之后就可以看到这个Excel2007文件的XML文件包结构如下： 
    _rels
    docProps
    xl
    [Content_Types].xml
1、[Content_Types].xml描述整个Excel内容的结构 - Excel各个XML文件联系成一个整体 
2、docProps文件夹
Excel存储格式\docProps文件夹中的xml记录了Excel文档的属性信息
core.xml ： 描述文件的创建时间和修改时间，标题，主题和作者
app.xml ：描述文档的其他属性，文档类型，版本，是否只读，是否共享，安全属性等文档属性信息。

3.xl文件夹
如果工作簿中有VB工程，则会出现相应的二进制文件
_rels文件夹 ： 描述了工作簿Workbook中各个xml之间的关系
printerSettings文件夹 ： 描述了打印设置
theme文件夹： 描述了是当前的设置导航栏的默认样式

4、 workbook.xml
  workbook.xml文件包含一对标签
  每个元素都代表Excel 2007文件中的一个工作，这里有4个sheet
  工作表name属性值表示工作表名称
  工作表state属性值表示当前工作表是否隐藏
  工作表r:id属性值表示当前工作表对应xml文件的索引，之间的关系在_rels/workbook.xml.rels.xml中定义 
  当前Excel： 
4.2 sharedStrings.xml
Excel将各个sheet中字符型单元格的值存放在sharedStrings.xml中，用于共享，相同的字符串只记录一次，节省了内存
每个sheet中字符型单元格的值通过索引获取sharedStrings.xml中的字符串，索引从0开始，即si从0开始
数值型单元格的值直接存放在sheet*.xml中
4.3 styles.xml
sheet中的每个单元格的样式都存放在styles.xml中 

4.4 sheet.xml
标签的ref表示当前Sheet的行列范围
如ref=”A1”,即可能表示当前Sheet只有A1单元格有值，也可能表示当前Sheet为空，表示没有值
如ref=”A1:C3”,表示当前Sheet行范围为第1行到第3行，列范围为第A列到第C列
当前基本单元格Sheet： 

4.5 字符型单元格
标签的spans属性记录当前行的列范围，是base 1的
标签中存放字符型单元格值在sharedStrings.xml中的索引 
当前字符型单元格Sheet： 

一. 解析步骤
Excel SLSX文件路径path
根据path获取文件流FileInputStream -fis
根据fis解析成OPCPackage实例对象 -pkg
根据pkg创建XSSFReader实例对象 -reader
使用SAX解析
创建XMLReader实例对象 -parser
设置内容处理器DefaultHandler -handler
开始处理

二、OPCPackage
POI根据xlsx文档的路径path获取到文件File - file
使用java.util.zip.ZipFile打开file文件 - zip
从zip中获取到[Content_Types].xml
解析[Content_Types].xml，记录解析出Excel各个xml名称：ArrayList
Excel解析成ZipPackage实例对象
首先把xlsx文档解析包装成ZipPackage实例对象


4. SAX解析XML
使用SAX解析XML，需要实现自己的处理类，需要继承DefaultHandler



。ECMA-376官网 给出了Office Open XML 文件格式的详细技术规范。


<c></c>标签就代表每个单元格，t="s"表示值为字符串，对应的字符串值可以在sharedStrings.xml里找到.
<row>标签对应每一行数据；
<c>标签对应每一格数据，r="A1"表示位置；
<v>对应值


第一：cell为空的两种情况： 1. cell中原来有数据，把数据清空后，cell为空，xml为：<c r="B2" />  
  2.cell原本就为空，xml为：不存在此节点 ， 如下不存在<c r="B1"></c>节点 
<c r="A1" s="1" t="s"> 
  <v>0</v>
</c> 
<c r="C1" s="1" t="s">
  <v>2</v> 
</c>


       <row r="1" spans="1:7" ht="33.75" customHeight="1"> row标签是表示每一行的数据，r表示第几行，其他几个都是这几行的样式  
              <c r="A1" s="9" t="s">c标签表示每个单元格的内容，这里A1 第一行的第一列，r表示位置，s表示这个单元格的样式，  
                                                   s=9对应style.xml的的index为9的样式即为这个单元格的样式，t=s表示这个单元格有值，里面的v标签即为值的id，id对应到sharedstring.xm里的id对应的值  
                     <v>2</v>  
              </c>  
              <c r="B1" s="10" />  没有t属性，表示这个单元格没有值设置  
              <c r="C1" s="10" />  
              <c r="D1" s="10" />  
              <c r="E1" s="10" />  
              <c r="F1" s="10" />  
       </row>  
       <row r="2" spans="1:7" ht="27.75" customHeight="1">  第二行  
              <c r="A2" s="3" t="s">  第二行第二列  
                     <v>1</v>  
              </c>  
              <c r="B2" s="4" t="s">  
                     <v>5</v>  
              </c>  
              <c r="C2" s="3" t="s">  
                     <v>0</v>  



 顶级的文件(比如说formula.xlsx)被叫做package(Open Packaging Conventions). 因为包(package)是被实现为一个标准ZIP包的, 它自动地提供了对文档的压缩。在包中有两种内部的组件: parts和items. 总的来说, parts包括文档内容和一些含有用来描述parts的元数据的items. Items可以被进一步的细分为relationship items和content-type items.

exel文件结构的文本形式展示如下：

_rels/.rels
[Content_Types].xml
docProps/app.xml
docProps/core.xml
xl/sharedStrings.xml
xl/styles.xml
xl/workbook.xml
xl/_rels/workbook.xml.rels
xl/worksheets/sheet1.xml

zip包里有两个共享资源池类型的文件：

sharedStrings.xml ：存储的是共享字符串常量池。
styles.xml ：存储的是共享样式表。
三、单元格的格式类型
技术规范的第18.18.11章节给出了单元格类型(Cell Type)的详细说明。PS: 本文所有的测试数据均由Mac版的Excel2016 生成。Win下可能会略有差别。

1. 布尔类型
类型值为 “b”， true映射成数字1。

<c r="C1" t="b">
    <v>1</v>
</c>
1

2. 日期类型
类型值为“d”,日期类型的值是个浮点数，poi包有工具类将这个浮点数转成日期的工具方法。

<c r="B1" s="1">
    <v>43203.396180555559</v>
</c>
1
2
3
3. 错误类型
类型值为“e”,表示这个单元格包含一个错误。

<row r="1">
    <c r="A1" t="e"/>
</row>
1
2
3
4. 内联字符串类型
类型值为“inlineStr”,表示这个单元格的字符串并没有用共享字符串池子的值。

<c r="A2" t="inlineStr">
    <is>
        <t>SXSSFWorkbook_1_0</t>
    </is>
</c>

5. 数字类型
类型值为“n”

<c r="A1">
    <v>12</v>
</c>
1
2
3
6. 共享字符串类型
类型值为“s”，v节点的值可以看做一个指针，指向共享字符串池子（对应文件sharedStrings.xml ）的字符串索引。

<c r="D1" t="s">
    <v>0</v>
</c>
1
2
3
6. 公式类型
类型值为“str”, 这个公式是对e求10次方的幂函数。

<c r="C6" s="1" vm="15" t="str">
    <f>EXP(10)</f>
    <v>22026.465794806718</v>
</c>
1
2
3
4
四、单元格c的属性分类
在sheetN.xml文件中，c节点代表一个单元格。

1. 坐标属性
属性名为“r”（Reference），属性值为该单元格在表格中的坐标位置，如“A1”， “C9”

2. 样式索引属性
属性名为“s”(Style Index)，属性值是个数字，是样式索引表（对应文件styles.xml ）的下标。

The index of this cell’s style. Style records are stored in the Styles Part.

3. 单元格数据类型属性
属性名为 “t” (Cell Data Type)，属性值代表当前单元格的类型。类型枚举前文已经详细阐述， 这里不再赘述。

An enumeration representing the cell’s data type.