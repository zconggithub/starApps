文件详解：
common.html:此html中主要是自己一些公用控件
fileUpload.html主要是文件上传【用的是BootStrap】

【BootStrap的用法：】
**①上传控件的用法：去官方下载插件bootstrap_file插件**
    插件下载完后可以去除不必要的文件
**②一、需要引用的资源<在自己的html或模板中引入如下资源>**
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/bootstrap-table/src/bootstrap-table.css">
    <script src="assets/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/bootstrap-table/src/bootstrap-table.js"></script>
    <link type="text/css" rel="stylesheet" href="bootstrap-fileinput-master/css/fileinput.css" />
    <script type="text/javascript" src="bootstrap-fileinput-master/js/fileinput.js"></script>
    <script type="text/javascript" src="bootstrap-fileinput-master/js/locales/zh.js"></script>
    因为该插件基于Bootstrap和jQuery，所以需要引入js和css文件，同时如果想要使插件语言变成中文，也要引入对应的语言文件，中文为zh.js

**③html代码部分**
<div class="container-fluid">
    <form id="form" action="upload/insert" method="post" enctype="multipart/form-data">
         <div class="row form-group">
            <label class="col-md-4">图片上传:</label>
            <div class="col-sm-12">
                <input id="input-id" name="file" multiple type="file" data-show-caption="true">
            </div>
        </div>
    </form>
</div>
注意：有的教程在input标签上加了class=”file”，这个class会导致插件一直是英文，即语言切换失效，解决方式很简单，把class=”file”去掉即可。

**④JS代码部分**【下面的js代码是用于初始化】
$(function () {
        initFileInput("input-id");
    })
    function initFileInput(ctrlName) {
        var control = $('#' + ctrlName);
        control.fileinput({
            language: 'zh', //设置语言
            uploadUrl: "upload/insert", //上传的地址
            allowedFileExtensions: ['jpg', 'gif', 'png'],//接收的文件后缀
            //uploadExtraData:{"id": 1, "fileName":'123.mp3'},
            uploadAsync: true, //默认异步上传
            showUpload: true, //是否显示上传按钮
            showRemove : true, //显示移除按钮
            showPreview : true, //是否显示预览
            showCaption: false,//是否显示标题
            browseClass: "btn btn-primary", //按钮样式
            //dropZoneEnabled: true,//是否显示拖拽区域
            //minImageWidth: 50, //图片的最小宽度
            //minImageHeight: 50,//图片的最小高度
            //maxImageWidth: 1000,//图片的最大宽度
            //maxImageHeight: 1000,//图片的最大高度
            //maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
            //minFileCount: 0,
            //maxFileCount: 10, //表示允许同时上传的最大文件个数
            enctype: 'multipart/form-data',
            validateInitialCount:true,
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
            msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",

        }).on('filepreupload', function(event, data, previewId, index) {     //上传中
            var form = data.form, files = data.files, extra = data.extra,
            response = data.response, reader = data.reader;
            console.log('文件正在上传');
        }).on("fileuploaded", function (event, data, previewId, index) {    //一个文件上传成功
            console.log('文件上传成功！'+data.id);

        }).on('fileerror', function(event, data, msg) {  //一个文件上传失败
            console.log('文件上传失败！'+data.id);


        })
    }
其中常用的基础参数、回调函数都有注释。
