# HoldButton
暗示用户<按钮可长按>的一种解决方案。

下载 demo 试一下 [app-debug.apk](https://github.com/LinXueyuanStdio/HoldButton/blob/master/README/app-debug.apk?raw=true)

|效果图|效果图|效果图|效果图|
|:---:|:---:|:---:|:---:|
| ![效果图](./README/demo3.gif) |![效果图](./README/demo1.gif)|![效果图](./README/demo2.gif)|![效果图](./README/demo.gif)|

## 使用
- Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:
```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
- Step 2. Add the dependency
```gradle
dependencies {
        implementation 'com.github.LinXueyuanStdio:holdbutton:master-SNAPSHOT'
}
```