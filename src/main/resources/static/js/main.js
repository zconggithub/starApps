/*
@description:public javaScript
@author:allen
 */
var _alert_title;//提示信息
var _alert_content;//提示内容
var _alert_ico;//提示图标
/**
 * @description:alert of widget
 * @param _alert_title
 * @param _alert_content
 * @param _alert_ico
 */


/**
 *初始化事件
 */
function initJs(){

}

function alert_widget(){

    $("#alert_widget").css('display','block'); // open the alert_widget

}
function close_window_Widget() {
    $("#alert_widget").css("display","none");// close the alert_widget
}

/**
 * @Author:allen
 * 关闭文件上传控件
 */
function close_file_Widget() {
    $("#upload_fileWidget").css("display","none");
}
/**
 * @Author:allen
 * 打开文件上传控件
 */
function upload_widget() {
    $("#upload_fileWidget").css('display','block'); // open the upload_fileWidget

}

/**
 * 单文件验证【格式】
 */
function validateFile() {
    
}


/**
 * @description: waiting for doing
 */
function waiting_widget() {

}
/*
@descripton:common 中按钮事件
 */
function button_Widget() {
    
}

/**
 * Ajax通过FormData上传文件
 1.使用<form>表单初始化FormData对象方式上传文件
 */
/*function uploadFile() {
    var formData =new FormData($('#uploadForm')[0]);
    console.log(formData);
    $.ajax({
        type:'post',
        url:"/starApp/uploadFile",
        cache: false,
        data:formData,
        processData: false,
        contentType: false,
        success : function(feed_data){
            console.log(feed_data);
            console.log("上传成功!");
        }
    })
}*/







