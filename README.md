# JSBURP保姆级文档



# <u>1.简介</u>



1.1 <u>**绕过前端js加密爆破&&加密数据**</u>

**jsburp**是基于`selenium`和`browsermobproxy`的前端爆破工具,研发初衷是为了解决在渗透测试过程中的前端js加密问题,前端js加密虽然是一种可逆的加密方式,但对安全研究员的需要有一定的技术要求,需要花费的时间不定

jsburp通过调用浏览器的方法,直接绕过前端加密,并将加密后的密码excel导出,可以直接爆破或者将加密数据导出后通过burpsuite爆破,

1.2 <u>**验证码爆破**</u>

验证码识别调用了ddddocr的api

```html
https://github.com/sml2h3/ddddocr
```

selenium自动截取浏览器验证码区域的图片,传入本地的api接口,实现验证码的识别爆破



# 2.配置

文件内集成了所需版本chrome浏览器,jdk1.8,python3,免去了配置环境的绝大部分麻烦

为了更方便的启动`建议`将本地的chrome浏览器升级到最新的版本 `103.0.5060.134（正式版本） （64 位）`

我们只需要安装解压后cert里的证书安装到chrome浏览器就好了

![image-20220731105352030](https://s2.loli.net/2022/07/31/YQpDgtn9a45zVs2.png)

## 安装证书步骤

**1.点击右上角,选择设置**

![image-20220731105833280](https://s2.loli.net/2022/07/31/Z8yrzSRJiu7nxV9.png)

**2.点击隐私设置和安全性,选择安全**

![image-20220731105958190](https://s2.loli.net/2022/07/31/PeO6VBksMovy2CG.png)

**3.点击管理证书**

![image-20220731110028165](https://s2.loli.net/2022/07/31/kFMIdmLSlYcQVeq.png)

**4.点击 导入 -> 下一步 -> 浏览**

![image-20220731112051214](https://s2.loli.net/2022/07/31/xoXbH5NwIUBctfC.png)

**5.选择cert目录下的证书,将证书导入到受信任的根证书颁发机构**

![image-20220731112142944](https://s2.loli.net/2022/07/31/KcBqHLiYv4hNzEe.png)

# 3.启动

**1,点击文件夹下的start.vbs直接可以启动**

![image-20220731112422426](https://s2.loli.net/2022/07/31/ZaOpJsSAtG67uD1.png)

**2.点击底部的启动ocr服务器,启动ddddocr的api服务**,

![image-20220731112520239](https://s2.loli.net/2022/07/31/2aUcdZwH5nVkQMq.png)

![image-20220731112614145](https://s2.loli.net/2022/07/31/bVLKulZz9DstHIy.png)

**3.点击底部的的测试连接,出现连接成功即可**

![image-20220731112708620](https://s2.loli.net/2022/07/31/3xQmSz5tfUe8hrl.png)

# 4.使用

**1.使用自己搭建的带有验证码的网站**

![image-20220731113102712](https://s2.loli.net/2022/07/31/RGL2hSzW7KcjZOo.png)

**2.点击f12,复制selector,依次填入**

![image-20220731113247158](https://s2.loli.net/2022/07/31/gUfxMJpX93Lev6T.png)

![image-20220731113449796](https://s2.loli.net/2022/07/31/LxXhPWTBz3il49Q.png)

**3.点击爆破,即可开始**

![image-20220731113557056](https://s2.loli.net/2022/07/31/GUfDg1Al7c2xeWz.png)

**4.出现浏览器代表成功**

# 5.账号密码指定

![image-20220731113712682](https://s2.loli.net/2022/07/31/jWeMPu36Jk4wVoz.png)

# 6.文档输出

爆破或者加密**完成后**输出在目录下的output文件夹下

![image-20220731113749827](https://s2.loli.net/2022/07/31/GwZIlgdP6c2qCab.png)

# 7.关闭浏览器显示

将congfig文件夹下congfig.properties里的openchrom修改为false

![image-20220731114021610](https://s2.loli.net/2022/07/31/1kqnt39ArD6Ih2f.png)
