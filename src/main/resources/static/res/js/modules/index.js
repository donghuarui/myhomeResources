/**
  项目JS主入口
  以依赖layui的layer和form模块为例
**/    
/*layui.define(['layer', 'form'], function(exports){
  var layer = layui.layer
  ,form = layui.form;
  
  layer.msg('Hello World');
  
  exports('index', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
}); */



//Demo
layui.use('form', function(){
  var form = layui.form();
  
  //监听提交
  form.on('submit(formDemo)', function(data){
    layer.msg(JSON.stringify(data.field));
    return false;
  });
});