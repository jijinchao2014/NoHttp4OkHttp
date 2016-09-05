# NoHttp4OkHttp
本库是对NoHttp和OkHttp的扩展，用OkHttp作为NoHttp的底层。

本库只有3个类，代码只有30k，更加稳定易于维护，代码易读。

NoHttp开源地址：[https://github.com/yanzhenjie/NoHttp](https://github.com/yanzhenjie/NoHttp)。  

OkHttp开源地址：[https://github.com/square/okhttp](https://github.com/square/okhttp)。  

严振杰的主页：[www.yanzhenjie.com](http://www.yanzhenjie.com)。
严振杰的博客：[blog.yanzhenjie.com](http://blog.yanzhenjie.com)。

QQ技术群来：547839514，加群请一定阅读[群行为规范](https://github.com/yanzhenjie/SkillGroupRule)。

## 替换NoHttp底层为OkHttp的原理
首先大家应该阅读下我对NoHttp的源码分析：  
[http://blog.csdn.net/yanzhenjie1003/article/details/52413226](http://blog.csdn.net/yanzhenjie1003/article/details/52413226)。  

其次要阅读替换OkHttp为NoHttp底层的教程：  
[http://blog.csdn.net/yanzhenjie1003/article/details/52439317](http://blog.csdn.net/yanzhenjie1003/article/details/52439317)。  

上了上面的文章后你会知道NoHttp的网络层是对外提供了一个接口`IRestConnection`，我们的OkHttp需要针对`IRestCOnnection`写一个实现类即可，具体教程请看上面第二篇博客。

## 如何引用
* 使用Gradle远程依赖，推荐
```groovy
compile 'com.yanzhenjie.nohttp:nohttp-okhttp:1.0.0'
```

Or Maven
```xml
<dependency>
  <groupId>com.yanzhenjie.nohttp</groupId>
  <artifactId>nohttp-okhttp</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```

* 使用Jar，下载项目后导入jar文件夹中的所有jar包。

## 使用介绍
基本用法NoHttp的所有用法不变，需要注意的两个地方：
1. 创建队列NoHttp的用法是NoHttp.newRequestQueue...，使用本库改为NoOkHttp.newRequestQueue...
2. 原来的同步请求是NoHttp.startSyncRequest...，使用本库改为NoOkHttp.startSyncRequest...

如果还有不明白的可以看demo或者加QQ技术群来讨论：547839514。

**其他创建请求等用法均不变**，更多用法请参考NoHttp：[https://github.com/yanzhenjie/NoHttp](https://github.com/yanzhenjie/NoHttp)。

#License
```text
Copyright 2016 Yan Zhenjie

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```