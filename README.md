# XMvp 👋

![XMvp](https://cdn.jsdelivr.net/gh/LoveLifeEveryday/FigureBed@master/typora202004/28/100029-925949.png)

An easy to use MVP frame using in the Java

这是一个基于 `Java` 的简单易用，利用快速开发的 `MVP` 框架

[![](https://jitpack.io/v/LoveLifeEveryday/XMvp.svg)](https://jitpack.io/#LoveLifeEveryday/XMvp)![Hex.pm badge](https://img.shields.io/badge/license-Apache%202-blue)![GitHub release (latest by date)](https://img.shields.io/github/v/release/LoveLifeEveryday/XPermissions)



## 一. 优势

- 基于 `MVP` 框架，**能够有效降低代码的耦合度**

- 内置 `Retrofit` + `RxJava2` + `ButterKnife` + `Glide` + `BaseRecyclerViewAdapterHelper` **无需手动引入**
- **内置 `Cookie` 持久化**，无需担心登陆注册的问题
- 内置**自动显示加载中和自动关闭加载中**的功能，无需手动编写，可选开启与否
- 内置**自动处理异常基类**
- 内置一系列工具

> 下面将会详细介绍

- 优美封装 **`RetrofitService`** ，让你使用起来得心应手
- 优美封装 `RxJava`，**封装简化回调**，让你享受自动化和简约的快乐

## 二. 使用

### 2.1 基本配置

- 在根目录的 `build.gradle` 中引入 `maven`

```groovy
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

- 在 `app\build.gradle` 中设置使用 `Java8` ，并引入 `butterknife-compiler` 和 `XMvp` 框架

> 注意：`butterknife-compiler` 一定要按照我给的版本号填写，以免出现版本不兼容的 `BUG`

![image-20200428171045205](C:\Users\xucanyou666\AppData\Roaming\Typora\typora-user-images\image-20200428171045205.png)

```groovy
    implementation 'com.github.LoveLifeEveryday:XMvp:1.0.1'
    annotationProcessor 'com.jakewharton:butterknife-compiler:10.2.0'
```

### 2.2 开始使用

#### 2.2.1.配置 `BaseApplication`

新建一个类（名字不限），`extends` 自 `XMvp` ，在 `initBaseUrl` 中填入 `BASE_URL` 

```java
public class BaseApplication extends XMvp {
    @Override
    public String initBaseUrl() {
        return Contants.BASE_URL;
    }
}

```

#### 2.2.2 新建一个网络请求 `API`

> `example` 如下

```java
public interface LoginApi {
    //登录
    @FormUrlEncoded
    @POST("user/login")
    Observable<XBaseBean<User>> login(@Field("username") String username, @Field("password") String password);
}
```

#### 2.2.3 新建对应的实体类

> - 这里的带有嵌套的实体类看似很复杂，可以通过一个 `GsonFormat` 插件一键生成
> - 但需要注意的是：不要将 `BaseBean` 的那一层导入到实体类中

```java
public class User {

    /**
     * collectIds : []
     * email :
     * icon :
     * id : 3
     * password : 111111
     * type : 0
     * username : 111111
     */

    public String email;
    public String icon;
    public int id;
    public String password;
    public int type;
    public String username;
    public List<?> collectIds;
    public String repassword;
}
```

#### 2.2.4 编写 `View` 接口

> 注意的是，需要继承 `XBaseView`

```java
public interface ILoginView extends XBaseView {

    /**
     * 显示登陆成功
     *
     * @param successMessage 成功信息
     */
    void showLoginSuccess(String successMessage);

    /**
     * 显示登陆失败
     *
     * @param errorMessage 失败信息
     */
    void showLoginFailed(String errorMessage);

    void doSuccess();

}
```

#### 2.2.5 编写 `Presenter`

> 注意:
>
> -  `Presenter` 需要 `extends` `XBasePresenter <这里填入你上面写的 View 接口>`
>
> - `new XBaseObserver` 一定要填写明确的泛型
>
> - 这里可选网络请求时是否自动开启加载中
>
>   `new XBaseObserver<XBaseBean<User>>(baseView)` 默认为不开启
>
>   `new XBaseObserver<XBaseBean<User>>(baseView, true)` 为开启

```java
public class LoginPresenter extends XBasePresenter<ILoginView> {
    LoginPresenter(ILoginView baseView) {
        super(baseView);
    }


    void login(String userName, String password) {
        XUtil.closeSoftKeyboard();
        addDisposable(retrofitService.createRs(LoginApi.class).login(userName, password),
                      //这里第二个参数可选，默认为不开启 加载中
                      //设置为 true ，则开启加载中
                new XBaseObserver<XBaseBean<User>>(baseView, true) {
                    @Override
                    public void onSuccess(XBaseBean<User> bean) {
                        baseView.showLoginSuccess("登录成功（￣▽￣）");
                        baseView.doSuccess();
                    }

                    @Override
                    public void onError(String msg) {
                        baseView.showLoginFailed(msg + "(°∀°)ﾉ");
                    }
                });
    }
}
```

#### 2.2.6 编写 `Activity`

> 注意：
>
> - `Activity` 需要`extends` `XBaseActivity<这里填你上面的 Presenter>`
> - `Activity` 需要 `implements` 你上面写的 `View` 接口

```java


public class MainActivity extends XBaseActivity<LoginPresenter> implements ILoginView {

    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.btn_login)
    Button mBtnLogin;

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    /**
     * 初始化布局信息，比如： mHomeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
     */
    @Override
    protected void initView() {
    }

    /**
     * 初始化数据信息，比如：presenter.getArticleList();
     */
    @Override
    protected void initData() {

    }

    @Override
    public void showLoginSuccess(String successMessage) {
        ToastUtil.showToast(successMessage);
    }

    @Override
    public void showLoginFailed(String errorMessage) {
        ToastUtil.showToast(errorMessage);
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        presenter.login(mEtName.getText().toString(), mEtPassword.getText().toString());
    }
}

```

### 2.3 工具类说明

> 为了开发的便捷与简约，笔者添加了一些常用的工具类，下面将为大伙一一道来

#### 2.3.1 `ActivityUtil`

-  `Activity` 跳转

```java
/**
 * 不用 finish 当前 Activity 时直接调用此方法
 *
 * @param classes 跳转到的 Activity 的 Class
 */
public static void startActivity(Class classes)
```

```java
/**
 * 需要 finish 当前 Activity 时调用此方法，布尔值参数传入 true
 *
 * @param classes  需要打开的 activity
 * @param isFinish 是否 finish 当前 activity
 */
public static void startActivity(Class classes, boolean isFinish)
```

-  `Activity` 关闭

```java
/**
 * 结束当前 activity
 *
 * @param activity activity
 */
public static void finishActivity(Activity activity)
```

```java
/**
 * 关闭所有 Activity
 */
public static void closeAllActivity()
```

#### 2.3.2 `LogUtil`

- 设置 `Log ` 开关

```java
public static void setIsLog(boolean isLog)
```

- 各种 `Log` 方法

```java
LogUtil.i(TAG,String)
LogUtil.d(TAG,String)
LogUtil.e(TAG,String)
```

#### 2.3.3 `SpUtil`

- `set/get` 方法

> 具体可以看下源码：[SpUtil](https://github.com/LoveLifeEveryday/XMvp/blob/master/library/src/main/java/com/xmvp/xcynice/util/SpUtil.java)

#### 2.3.4 `ToastUtil`

```java
public static void showToast(final String msg)
```

```java
public static void showCenterToast(final String msg)
```

```java
public static void cancelToast()
```

#### 2.3.5 `XUtil`

```java
public static void closeSoftKeyboard()
```

> 上面的工具类说明，只是大致说明下主要方法，如果想要更详细了解的话，可以看下笔者的源码：[XMvp.Util](https://github.com/LoveLifeEveryday/XMvp/tree/master/library/src/main/java/com/xmvp/xcynice/util)

## How to create this

如果你也想封装一套这样的框架的话，笔者特地写了一篇文章，里面阐述了笔者创建这套框架的思路

[带你封装自己的 MVP+Retrofit+RxJava2 框架（二）](https://juejin.im/post/5e520db1e51d45270c277ca8)

## Author

👤 **许朋友爱玩**

- `Github` ： https://github.com/LoveLifeEveryday/
- 个人博客：http://xcynice.xyz/
- 掘金：https://juejin.im/user/5e429bbc5188254967066d1b/posts

## Show your support

Give a ⭐️ if this project helped you!

求星星⭐️，求赞👍