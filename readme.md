# 阿里Hotfix2.0热更初体验
>本来这是dagger2的学习demo（看项目名字也能看的出来），偶尔看
到hotfix技术团队的博客，升级2.0之后相对于1.x版本来说，功能更
强大，继承更加简单，再想想前一段时间对接微信的Tinker（本人感
觉热更方案中最难集成的），所以来测试一下。

### 配置
#### gradle远程仓库依赖
 打开项目找到Project的build.gradle文件，添加如下配置：
添加maven仓库地址：

    repositories {
       maven {
       url "http://maven.aliyun.com/nexus/content/repositories/releases"
       }
    }

打开app的build.gradle文件，添加gradle依赖：

    dependencies {
    compile 'com.aliyun.ams:alicloud-android-hotfix:3.0.2'
    }
传递性依赖utdid这个sdk, 所以不需要重复依赖utdid.但是另一方
面其它阿里系SDK也可能依赖了utdid这个SDK, 如果编译期间报u
tdid重复, 所以此时进行如下处理即可, 关闭传递性依赖:

     compile ('com.aliyun.ams:alicloud-android-hotfix:3.0.0') {
         exclude(module:'alicloud-android-utdid')
    }
utdid实际上是为设备生成唯一deviceid的一个基础类库



## 权限配置

Sophix SDK使用到以下权限

    <! -- 网络权限 -->
    <uses-permission an droid:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <! -- 外部存储读权限 -->
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>

## 配置AndroidManifest文件
在AndroidManifest.xml中间的application节点下添加如下配置：

        <meta-data
        android:name="com.taobao.android.hotfix.IDSECRET"
        android:value="App ID" />
        <meta-data
        android:name="com.taobao.android.hotfix.APPSECRET"
        android:value="App Secret" />
        <meta-data
        android:name="com.taobao.android.hotfix.RSASECRET"
        android:value="RSA密钥" />
将上述value中的值分别改为通过平台HotFix服务申请得到的App Secret和RSA密钥。
## 混淆配置

    #基线包使用，生成mapping.txt
    -printmapping mapping.txt
    #生成的mapping.txt在app/buidl/outputs/mapping/release路径下，移动到/app路径下
    #修复后的项目使用，保证混淆结果一致
    #-applymapping mapping.txt
    #hotfix
    -keep class com.taobao.sophix.**{*;}
    -keep class com.ta.utdid2.device.**{*;}
    #防止inline
    -dontoptimize

混淆打开的话需要在proguard-rules.pro文件中进行如下配置，没有的话就不用了。
## 接入范例

initialize的调用应该尽可能的早，必须在Application.onCreate()的最开始进行SDk初始化操作，否则极有可能导致崩溃。而查询服务器是否有可用补丁的操作可以在后面的任意地方。

    SophixManager.getInstance().setContext(this)
                    .setAppVersion(appVersion)
                    .setAesKey(null)
                    .setEnableDebug(true)
                    .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                        @Override
                        public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                            // 补丁加载回调通知
                            if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                                // 表明补丁加载成功
                            } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                                // 表明新补丁生效需要重启. 开发者可提示用户或者强制重启;
                                // 建议: 用户可以监听进入后台事件, 然后应用自杀
                            } else if (code == PatchStatus.CODE_LOAD_FAIL) {
                                // 内部引擎异常, 推荐此时清空本地补丁, 防止失败补丁重复加载
                                // SophixManager.getInstance().cleanPatches();
                            } else {
                                // 其它错误信息, 查看PatchStatus类说明
                            }
                        }
                    }).initialize();
    SophixManager.getInstance().queryAndLoadNewPatch();

## 其他
热更需要的tools在阿里云上都可以下载到~还有详细的官方文档。

https://help.aliyun.com/document_detail/53287.html?spm=5176.doc53287.6.554.6o0Y9T


app-release-1.1.0-2017-06-18-14-14-38.apk   oldapk

app-release-1.1.0-2017-06-18-14-16-04.apk   newapk
