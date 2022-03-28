# AJAX笔记

## Ajax简介

**A：Asynchronous**

**J：JavaScript**

**A：And**

**X：Xml**

*JavaScript：Ajax技术主要开发语言*

*HTML/XML/JSON：用于封装请求数据和响应数据*

*DOM：通过DOM修改页面元素，实现页面局部刷新*

*CSS：改变样式，美化页面效果，提升用户体验*

*Ajax引擎（核心）：即XMLHttpRequest对象，以异步的方式在客户端和服务器之间传递数据*

## Ajax特点

#### 不刷新页面

- 有效利用带宽
- 连续用户体验
- 提供类似C/S的交互效果，操作更方便

## Ajax与传统web差异

![image-20210314114122794](C:\Users\10293\AppData\Roaming\Typora\typora-user-images\image-20210314114122794.png)

## Ajax工作流程

![image-20210314120136621](C:\Users\10293\AppData\Roaming\Typora\typora-user-images\image-20210314120136621.png)

## XMLHttpRequest对象

- JavaScript对象**XMLHttpRequest**是整个Ajax技术的核心，它提供了异步发送请求的能力

### 工厂函数创建XMLHttpRequest对象

```javascript
//定义工厂函数
function createXHR() { //用于创建XMLHttpRequest对象，兼容不同的浏览器
    if(window.XMLHttpRequest){
        return new XMLHttpRequest();
    }
    else //IE老版本浏览器
    {
        return new ActiveXObject("Microsoft.XMLHTTP");
    }
}
```

### 常用方法

- **open(method，URL，async）**

  ```javascript
  //建立与服务器的连接
  //method参数：请求方式，可选值是GET（默认）或者是POST
  //URL参数：请求地址
  //async参数：是否使用异步请求，可选值是true或者false
  ```

- **send(content)**

  ```javascript
  //发送请求
  //content参数：请求参数，只有post请求才能通过send()发送参数
  ```

- **setRequestHeader(header,value)**

  ```javascript
  //设置请求头信息
  ```

### 常用属性

- readyState：表示XMLHttpRequest对象的状态
  1. `0`：XMLHttpRequest对象已经创建，还没有完成初始化
  2. `1`：XMLHttpRequest对象开始发送请求
  3. `2`：XMLHttpRequest对象请求发送完成
  4. `3`：XMLHttpRequest对象开始读取响应，还没有结束
  5. `4`：XMLHttpRequest对象读取响应结束

- status：表示HTTP请求响应的状态
  1. `200`：服务器响应正常
  2. `400`：无法找到请求的资源
  3. `403`：没有访问权限
  4. `404`：访问的资源不存在
  5. `500`：服务器内部错误

### 常用事件

- **onreadystatechange**：事件属性，指定**回调函数**，当readyState属性改变时触发执行

  ```javascript
  // 指定事件回调函数，当就绪状态码发生改变时执行(回调函数一定要在发送send()之前绑定)
  xhr.onreadystatechange = function (){
      // alert(xhr.readyState); // 打印状态就绪码
      // alert(xhr.status); // http请求响应的状态
      // &&两边的顺序不能颠倒，要先判断readyState后判断status
      if(xhr.readyState == 4 && xhr.status ==200){ // 就绪状态是4、状态码是200才能处理服务器数据
          var data = xhr.responseText; //无法获取响应数据
          alert(data);
      }
  }
  ```

## GET和POST两种方式的区别

### **GET方式**

​    1.数据通过url发送,显示在地址栏中

​    2.传输数据大小有限

​    3.字符集会被自动转化成**ISO-8859-1** (一般在服务器端进行解码)

​	4.要传递参数的时候通过open方法中的url进行传输

```js
xhr.open("get","checkUsername?username="+username,true);
```

​    解决乱码:

​        1.服务器端解码

​        2.使用过滤器

​        3.直接修改tomcat的配置文件server.xml(Connector中增加URIEncoding="utf-8")（推荐方式）

### **POST方式**

​    1.数据通过请求头发送,不显示在地址栏中,相比较而言更安全

​    2.理论上传输数据大小没有限制

​    3.**字符集可以在请求头中设置**,服务端自动识别

​    4.**文件上传时必须使用POST方式**

​    5.POST方式相比较于GET方式更安全，但需要注意的是两者**都是以明文传输**

​	6.要传递参数的时候通过send方法进行传参

```js
xhr.send("username="+username);
```

​    解决乱码:

​        1.服务器端设置字符集：**HttpServletRequest.setCharacterEncoding("uft-8")**

​        2.使用过滤器（推荐方式）

​        3.客户端设置请求头

## 原生JavaScript实现Ajax

```js
// 发送异步请求
function sendAsync() {
    var xhr = new createXHR();
    // 指定事件回调函数，当就绪状态码发生改变时执行(回调函数一定要在发送send()之前绑定)
    xhr.onreadystatechange = function (){
        // alert(xhr.readyState); // 打印状态就绪码
        // alert(xhr.status); // http请求响应的状态
        // &&两边的顺序不能颠倒，要先判断readyState后判断status
        if(xhr.readyState == 4 && xhr.status ==200){ // 就绪状态是4、状态码是200才能处理服务器数据
            var data = xhr.responseText; //获取响应数据
            alert(data);
        }
    }
    // 建立异步请求
    xhr.open("get","/simple",true);
    xhr.send(null);
}
```



## 使用JQuery实现Ajax

### 传统方式（js）的不足

- 方法、属性、常用值较多不好记忆
- 步骤繁琐
- 浏览器兼容问题

### jQuery常用Ajax方法

- $.ajax()发送HTTP请求加载远程数据，最底层Ajax实现
- $.get()发送HTTP GET请求从服务器加载数据
- $.post()发送HTTP POST请求从服务器加载数据
- $.getJSON()发送HTTP请求从服务器加载JSON数据
- load()发送Ajax请求从服务器加载数据，并把返回的数据放置到指定的元素中，最简单的方式

### $.ajax()发送Ajax请求

语法：$.ajax([settings])

#### 常用属性

![image-20210316100436804](C:\Users\10293\AppData\Roaming\Typora\typora-user-images\image-20210316100436804.png)

#### 常用函数

![image-20210316102332616](C:\Users\10293\AppData\Roaming\Typora\typora-user-images\image-20210316102332616.png)

#### 示例

```js
// 使用$.ajax()发送Ajax请求
$.ajax({
    type:"post",  // 指定请求的类型，默认是get
    url:"login",  // 请求地址
    async:true,   // 是否进行异步请求
    // data:"username=" + usernameValue  + "&userpwd=" + userpwdValue, //发送的数据
    // Json形式传输数据,传到服务器参数名要加上字符串
    data:{
        "username":usernameValue,
        "userpwd":userpwdValue
    },
    dataType:"text", // 返回数据类型，默认是text
    // ajax请求成功时的回调函数
    success:function (data) {
        if(data=="true"){
            document.getElementById("loginDiv").hidden=true;
            document.getElementById("info").innerText="欢迎您! "+usernameValue;
        }
        else{
            alert("用户名或者密码错误!");
        }
    },
    // ajax请求失败的回调函数
    error:function () {
        document.getElementById("info").innerText="ajax请求失败";
    },
    // 发送请求前的回调函数
    beforeSend:function () {
        document.getElementById("info").innerText="登录中......";
    }
})
```

### $.get()发送Ajax请求

语法：**$.get(url,data,success(resp,status,xhr),dataType)**

#### 常用参数

![image-20210316115033703](C:\Users\10293\AppData\Roaming\Typora\typora-user-images\image-20210316115033703.png)

### $.post()发送Ajax请求

语法：$.post(url,data,success(resp,status,xhr),dataType)

###### 常用参数

![image-20210316115033703](C:\Users\10293\AppData\Roaming\Typora\typora-user-images\image-20210316115033703.png)

###### 示例

```javascript
function doLogin2() {
    var usernameValue = document.getElementById("username").value;
    var userpwdValue = document.getElementById("userpwd").value;
    // 使用$.post()发送Ajax请求
    $.post("login",{
        "username":usernameValue,
        "userpwd":userpwdValue
    },function (data) {
        if(data=="true"){
            document.getElementById("loginDiv").hidden=true;
            document.getElementById("info").innerText="欢迎您! "+usernameValue;
        }
        else{
            alert("用户名或者密码错误!");
        }
    },"text")
}
```

### $.getJSON()发送Ajax请求

##### $.getJSON(url,data,success(result,status,xhr))

###### 常用参数

![image-20210316133826549](C:\Users\10293\AppData\Roaming\Typora\typora-user-images\image-20210316133826549.png)

*注：在jQuery中可以使用$.getJSON()方法发送JSON格式的数据到服务器端，或接收从服务器端返回的JSON格式的数据*。

### $(selector).load()发送Ajax请求

##### $(selector).load(url,data,function(result,status,xhr))

常用参数

![image-20210316134342459](C:\Users\10293\AppData\Roaming\Typora\typora-user-images\image-20210316134342459.png)

#### 序列化表单

```javascript
// 能够对表单中含还有name属性的元素进行序列化，生成能够直接用于请求的参数字符串
// 可用于ajax请求中
console.log($("#myform").serialize()); // username=sadasd&userpwd=asdsads

<div id="loginDiv">
    <form action="" id="myform">
        用户名：<input type="text" id="username" name="username">
        密　码：<input type="password" name="userpwd" id="userpwd">
        <input type="button" value="登录" onclick="doLogin()">
    </form>
</div>
```

注：只对form表单内的元素起作用！

