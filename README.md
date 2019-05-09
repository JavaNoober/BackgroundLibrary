# BackgroudLibrary
A framework for directly generating shape through Tags, no need to write shape.xml again（通过标签直接生成shape，无需再写shape.xml）  


![](https://user-gold-cdn.xitu.io/2018/9/11/165c6e5c0cff0548?w=681&h=233&f=png&s=31240)


[English.md](https://github.com/JavaNoober/BackgroundLibrary/blob/master/README-EN.md)

依赖方式：

    implementation "com.android.support:appcompat-v7:$supportVersion"
    implementation 'com.noober.background:core:1.4.5'

版本更新：
    
    1.0.5 解决android 8以下Fragment中view显示问题，解决水波纹不能与press事件共存问题，优化水波纹显示
    1.2.0 增加对selector的支持
    1.2.2 修复部分属性不生效的bug
    1.2.3 checkbox radiobutton使用的bug
    1.2.4 新增了text不同状态变色的支持
    1.2.5 去除kotlin依赖
    1.2.6 修复调用其他换肤框架可能失效的问题
    1.2.7 新增pressed_stroke_color等边框的属性
    1.3.0 提供通过代码生成Drawable的方法；为了防止属性冲突，所有属性以bl_开头
    1.3.1 优化代码，减少内存占用
    1.3.2 修复与其他框架冲突的bug
    1.3.3 修复通过代码创建drawable，白色失效的问题
    1.3.4 支持最小sdk从16变为14，如果minSdkVersion < 16:bl_gradient_angle, bl_gradient_startColor, bl_gradient_centerColor, bl_gradient_endColor会失效，其他正常
    1.3.5 新增属性bl_checked_button_drawable与bl_unChecked_button_drawable，在checkBox与radiobutton中使用了bl_checked_drawable与bl_unChecked_drawable会与之前版本有区别，
        bl_checked_button_drawable相当于setButtonDrawable，而bl_checked_drawable相当于setBackground，详见demo的radiobutton写法
    1.3.6 修复bug
    1.3.7 新增属性，使用详见[例子4](#jump1)
    1.3.8 新增属性bl_position，可以设置drawable的位置left top right bottom
    1.4.0 增加预览功能
    1.4.1 增加帧动画属性
    1.4.2 增加属性bl_activated_textColor, bl_active_textColor, bl_expanded_textColor, bl_unActivated_textColor, bl_unActive_textColor, bl_unExpanded_textColor以及BLConstraintLayout
    1.4.3 1.4.3开始自动加入混淆配置，无需自己配置
    1.4.4 修复与kotlin直接通过id获取view冲突的问题，并且可以在代码内直接使用BLView
    1.4.5 新增属性bl_multi_selector1~bl_multi_selector6支持selector的一条属性多个状态的设置


## 示例效果

![](https://raw.githubusercontent.com/JavaNoober/BackgroundLibrary/master/test/show.gif)

通过配置Live Templates，实现自动代码提示  
![](https://raw.githubusercontent.com/JavaNoober/BackgroundLibrary/master/test/属性提示.gif)


## 使用方法
1、在BaseActivity中的super.onCreate之前调用。**如果minSdkVersion < 16:bl_gradient_angle, bl_gradient_startColor, bl_gradient_centerColor, bl_gradient_endColor会失效，其他正常**
    
    BackgroundLibrary.inject(context);
    
2、在layout中直接添加属性即可。

[博文使用介绍](https://github.com/JavaNoober/BackgroundLibrary/blob/master/%E4%BD%BF%E7%94%A8%E4%BB%8B%E7%BB%8D.md)

## 支持的自定义属性
下面是所有的自定义属性，使用方法和shape、selector完全一样
### shape类
支持shape的所有属性，命名规则就是**标签名_标签属性名**：

| 名称 | 类型 |
|---|---|
|bl_shape|rectangle、oval、line、ring(暂时不支持)|
|bl_solid_color|color|
|bl_corners_radius|dimension|
|bl_corners_bottomLeftRadius|dimension|
|bl_corners_bottomRightRadius|dimension|
|bl_corners_topLeftRadius|dimension|
|bl_corners_topRightRadius|dimension|
|bl_gradient_angle|integer|
|bl_gradient_centerX|float|
|bl_gradient_centerY|float|
|bl_gradient_centerColor|color|
|bl_gradient_endColor|color|
|bl_gradient_startColor|color|
|bl_gradient_gradientRadius|dimension|
|bl_gradient_type|linear、radial、sweep|
|bl_gradient_useLevel|boolean|
|bl_size_width|dimension|
|bl_size_height|dimension|
|bl_stroke_width|dimension|
|bl_stroke_color|color|
|bl_stroke_dashWidth|dimension|
|bl_stroke_dashGap|dimension|
|bl_position|left、right、top、bottom|

### selector类
支持selector的所有属性：

| 名称 | 类型 |
|---|---|
|bl_checkable_drawable|color、reference|
|bl_checked_drawable|color、reference|
|bl_enabled_drawable|color、reference|
|bl_selected_drawable|color、reference|
|bl_pressed_drawable|color、reference|
|bl_focused_drawable|color、reference|
|bl_focused_hovered|color、reference|
|bl_focused_activated|color、reference|
|bl_unCheckable_drawable|color、reference|
|bl_unChecked_drawable|color、reference|
|bl_unEnabled_drawable|color、reference|
|bl_unSelected_drawable|color、reference|
|bl_unPressed_drawable|color、reference|
|bl_unFocused_drawable|color、reference|
|bl_unFocused_hovered|color、reference|
|bl_unFocused_activated|color、reference|
|关于text颜色的设置||
|bl_checkable_textColor|color|
|bl_checked_textColor|color|
|bl_enabled_textColor|color|
|bl_selected_textColor|color|
|bl_pressed_textColor|color|
|bl_focused_textColor|color|
|bl_activated_textColor|color|
|bl_active_textColor|color|
|bl_expanded_textColor|color|
|bl_unCheckable_textColor|color|
|bl_unChecked_textColor|color|
|bl_unEnabled_textColor|color|
|bl_unSelected_textColor|color|
|bl_unPressed_textColor|color|
|bl_unFocused_textColor|color|
|bl_unActivated_textColor|color|
|bl_unActive_textColor|color|
|bl_unExpanded_textColor|color|
|关于checkbox、radiobutton的buttonDrawable的设置||
|bl_checked_button_drawable|color、reference|
|bl_unChecked_button_drawable|color、reference|

### 动画属性
| 名称 | 类型 |
|---|---|
|bl_oneshot|是否只显示一次|
|bl_anim_auto_start|是否自动播放|
|bl_duration|每一帧动画时长|
|bl_duration_item0|第0帧动画时长|
|bl_duration_item1|第1帧动画时长|
|bl_duration_item2|第2帧动画时长|
|bl_duration_item3|第3帧动画时长|
|.|.|
|.|.|
|.|.|
|bl_duration_item12|第12帧动画时长|
|bl_duration_item13|第13帧动画时长|
|bl_duration_item14|第14帧动画时长|
|bl_frame_drawable_item0|第0帧动画|
|bl_frame_drawable_item1|第1帧动画|
|bl_frame_drawable_item2|第2帧动画|
|bl_frame_drawable_item3|第3帧动画|
|.|.|
|.|.|
|.|.|
|bl_frame_drawable_item12|第12帧动画|
|bl_frame_drawable_item13|第13帧动画|
|bl_frame_drawable_item14|第14帧动画|

### 其他属性(sdk21及以上的手机才支持)
| 名称 | 类型 |备注|
|---|---|---|
|bl_ripple_enable|boolean|是否开启点击的水波纹效果|
|bl_ripple_color|color|水波纹颜色（如果开启，一定要有这个属性能生效）|
|bl_checkable_stroke_color| color| 边框状态的属性，如果在sdk21以下，会没有效果，默认固定边框色取stroke_color的值|
|bl_checked_stroke_color| color| |
|bl_enabled_stroke_color| color| |
|bl_selected_stroke_color| color| |
|bl_pressed_stroke_color| color| |
|bl_focused_stroke_color| color| |
|bl_unCheckable_stroke_color| color| |
|bl_unChecked_stroke_color| color| |
|bl_unEnabled_stroke_color| color| |
|bl_unSelected_stroke_color| color| |
|bl_unPressed_stroke_color| color| |
|bl_unFocused_stroke_color| color| |
|bl_checkable_solid_color| color| 填充颜色的属性，如果在sdk21以下，会没有效果，默认固定边框色取solid_color的值|
|bl_checked_solid_color| color| |
|bl_enabled_solid_color| color| |
|bl_selected_solid_color| color| |
|bl_pressed_solid_color| color| |
|bl_focused_solid_color| color| |
|bl_unCheckable_solid_color| color| |
|bl_unChecked_solid_color| color| |
|bl_unEnabled_solid_color| color| |
|bl_unSelected_solid_color| color| |
|bl_unPressed_solid_color| color| |
|bl_unFocused_solid_color| color| |

### 代码生成Drawable的Api,详情见DrawableCreator类
 
所有的属性都提供了**set方法**去通过代码设置，具体见[DrawableCreator.Build](https://github.com/JavaNoober/BackgroundLibrary/blob/develop/library/src/main/java/com/noober/background/drawable/DrawableCreator.java)
使用方法如下：

    //设置button圆角背景
    Drawable drawable = new DrawableCreator.Builder().setCornersRadius(dip2px(20))
                    .setGradientAngle(0).setGradientColor(Color.parseColor("#63B8FF"), Color.parseColor("#4F94CD")).build();
    btn.setBackground(drawable);
    //文字点击变色
    tvTest1.setClickable(true);//由于Android源码的原因，必须调用，否则不生效
    ColorStateList colors = new DrawableCreator.Builder().setPressedTextColor(Color.RED).setUnPressedTextColor(Color.BLUE).buildTextColor();
    tvTest1.setTextColor(colors);

注意，下面属性只有api>21生效，如果小于21，会使用默认值：

| 名称 | 类型 |备注|
|---|---|---|
|bl_checkable_stroke_color| color| 边框状态的属性，如果在sdk21以下，会没有效果，默认固定边框色取stroke_color的值|
|bl_checked_stroke_color| color| |
|bl_enabled_stroke_color| color| |
|bl_selected_stroke_color| color| |
|bl_pressed_stroke_color| color| |
|bl_focused_stroke_color| color| |
|bl_unCheckable_stroke_color| color| |
|bl_unChecked_stroke_color| color| |
|bl_unEnabled_stroke_color| color| |
|bl_unSelected_stroke_color| color| |
|bl_unPressed_stroke_color| color| |
|bl_unFocused_stroke_color| color| |
|bl_checkable_solid_color| color| 填充颜色的属性，如果在sdk21以下，会没有效果，默认固定边框色取solid_color的值|
|bl_checked_solid_color| color| |
|bl_enabled_solid_color| color| |
|bl_selected_solid_color| color| |
|bl_pressed_solid_color| color| |
|bl_focused_solid_color| color| |
|bl_unCheckable_solid_color| color| |
|bl_unChecked_solid_color| color| |
|bl_unEnabled_solid_color| color| |
|bl_unSelected_solid_color| color| |
|bl_unPressed_solid_color| color| |
|bl_unFocused_solid_color| color| |

    Drawable drawable3 = new DrawableCreator.Builder()
            .setCornersRadius(dip2px(20))
            .setSolidColor(Color.parseColor("#7CFC00"))//此处相当于api < 21 的默认值
            .setStrokeColor(Color.parseColor("#7CFC00"))//此处相当于api < 21 的默认值
            .setPressedSolidColor(Color.parseColor("#8c6822"), Color.parseColor("#71C671"))
            .setPressedSolidColor(Color.parseColor("#8c6822"), Color.parseColor("#71C671"))
            .setStrokeWidth(dip2px(1))
            .build();
 
 
### bl_multi_selector属性
| 名称 | 类型 |备注|
|---|---|---|
|bl_multi_selector1| String| 支持selector的一条属性设置同时设置多个状态的写法,内容规则为以",
"为分隔符，最后一项为drawable资源id的名字，可选状态为state_checkable，state_checked，state_enabled，state_selected，state_pressed，state_focused，state_hovered
，state_activated，默认为true，如果为false则在前面加上"-"即可，例如-state_checkable,具体可以查看使用例子8|
|bl_multi_selector2| String| 同上|
|bl_multi_selector3| String| 同上|
|bl_multi_selector4| String| 同上|
|bl_multi_selector5| String| 同上|
|bl_multi_selector6| String| 同上|

bl_multi_selector使用示例：
下例中的test_sel1，item中同时设置了android:state_pressed="true" android:state_focused="true",则用bl_multi_selector1设置为：app:bl_multi_selector1="state_pressed,state_focused,test_bg_sel"


    <EditText
        android:layout_width="300dp"
        android:layout_height="36dp"
        android:layout_marginTop="15dp"
        android:gravity="center"
        android:clickable="true"
        android:text="一条属性多个状态（原生写法）"
        android:textColor="@android:color/black"
        android:textSize="18dp"
        android:textStyle="bold"
        android:background="@drawable/test_sel1"/>

    test_sel1:
    <selector xmlns:android="http://schemas.android.com/apk/res/android">
        <item android:state_pressed="true" android:state_focused="true"
            android:drawable="@drawable/test_bg_sel" /> <!-- pressed -->
        <item android:state_pressed="false" android:state_focused="false"
            android:drawable="@drawable/test_bg_sel2" /> <!-- focused -->
    </selector>
    bl_multi_selector：用法，最多支持6条

     <EditText
        android:padding="0dp"
        android:layout_width="300dp"
        android:layout_height="36dp"
        android:layout_marginTop="15dp"
        android:clickable="true"
        android:gravity="center"
        android:text="一条属性多个状态（原生写法）"
        android:textColor="@android:color/black"
        android:textSize="18dp"
        android:textStyle="bold"
        app:bl_multi_selector1="state_pressed,state_focused,test_bg_sel"
        app:bl_multi_selector2="-state_pressed,-state_focused,test_bg_sel2"/>

## 使用例子

1.边框+背景+圆角

    <TextView
        android:layout_width="130dp"
        android:layout_width="130dp"
        android:layout_height="36dp"
        android:gravity="center"
        android:text="TextView"
        android:textColor="#8c6822"
        android:textSize="20sp"
        app:bl_corners_radius="4dp"
        app:bl_solid_color="#E3B666"
        app:bl_stroke_color="#8c6822"
        app:bl_stroke_width="2dp" />
等同于

    <shape xmlns:android="http://schemas.android.com/apk/res/android">
        <corners android:radius="2dp"/>
        <solid android:color="#E3B666"/>
        <stroke android:color="#E3B666" android:width="2dp"/>
    </shape>

2、渐变

    <shape xmlns:android="http://schemas.android.com/apk/res/android">
        <corners android:radius="2dp"/>
        <gradient android:angle="0" 
                  android:startColor="#63B8FF"
                  android:endColor="#4F94CD"/>
    </shape>
    
等同于

     <Button
        android:id="@+id/btn"
        android:layout_width="130dp"
        android:layout_height="36dp"
        android:layout_marginTop="5dp"
        android:gravity="center"
        android:padding="0dp"
        android:text="跳转到列表"
        android:textColor="#4F94CD"
        android:textSize="20sp"
        app:bl_corners_radius="2dp"
        app:bl_gradient_angle="0"
        app:bl_gradient_endColor="#4F94CD"
        app:bl_gradient_startColor="#63B8FF" />
    
点击效果

![](https://user-gold-cdn.xitu.io/2018/9/12/165ce0e7226b6e05?w=264&h=68&f=gif&s=293851)

第一个点赞效果：

    android:layout_width="20dp"
    android:layout_height="20dp"
    android:layout_marginTop="5dp"
    app:bl_pressed_drawable="@drawable/circle_like_pressed"
    app:bl_unPressed_drawable="@drawable/circle_like_normal" />
    
就等同于:  

    <selector xmlns:android="http://schemas.android.com/apk/res/android">
        <item android:state_pressed="true"
            android:drawable="@drawable/circle_like_pressed" />
        <item android:state_pressed="false"
            android:drawable="@drawable/circle_like_normal" />
    </selector>
    

通过代码设置：

    Drawable drawable4 = new DrawableCreator.Builder().setCornersRadius(dip2px(20))
            .setPressedDrawable(ContextCompat.getDrawable(this, R.drawable.circle_like_pressed))
            .setUnPressedDrawable(ContextCompat.getDrawable(this, R.drawable.circle_like_normal))
            .build();
    tv.setClickable(true);
    tv.setBackground(drawable4);   
    
第二个按钮效果：

    <Button
            android:layout_width="300dp"
            android:layout_height="50dp"
            android:layout_marginTop="5dp"
            android:gravity="center"
            android:padding="0dp"
            android:text="有波纹触摸反馈的按钮"
            android:textColor="@android:color/white"
            android:textSize="20sp"
            app:bl_corners_radius="20dp"
            app:bl_pressed_drawable="#71C671"
            app:bl_ripple_color="#71C671"
            app:bl_ripple_enable="true"
            app:bl_stroke_color="#8c6822"
            app:bl_stroke_width="2dp"
            app:bl_unPressed_drawable="#7CFC00" />

通过代码设置：

    Drawable drawable3 = new DrawableCreator.Builder().setCornersRadius(dip2px(20))
            .setRipple(true, Color.parseColor("#71C671"))
            .setSolidColor(Color.parseColor("#7CFC00"))
            .setStrokeColor(Color.parseColor("#8c6822"))
            .setStrokeWidth(dip2px(2))
            .build();
    btn.setBackground(drawable3);

使用其实基本和selector shape一样。

3.点击文字变色
![](https://user-gold-cdn.xitu.io/2018/9/19/165f131f7e85b1e7?w=289&h=61&f=gif&s=8828)  

    <Button
        android:layout_width="300dp"
        android:layout_height="50dp"
        android:layout_marginTop="5dp"
        android:gravity="center"
        android:padding="0dp"
        android:text="点击文字变色"
        app:bl_pressed_textColor="#919DAF"
        app:bl_unPressed_textColor="@android:color/holo_red_dark"/>


4.<span id="jump1">点击填充边框变色属性</span>

![](https://raw.githubusercontent.com/JavaNoober/BackgroundLibrary/master/test/pic11.gif)

    <TextView
        android:layout_width="180dp"
        android:layout_height="36dp"
        android:layout_marginTop="15dp"
        android:gravity="center"
        android:text="点击边框变色"
        android:textColor="@android:color/black"
        android:textSize="18dp"
        android:textStyle="bold"
        android:clickable="true"
        app:bl_pressed_solid_color="#FFDEAD"
        app:bl_unPressed_solid_color="#E9967A"
        app:bl_stroke_width="1dp"
        app:bl_pressed_stroke_color="#C6E2FF"
        app:bl_unPressed_stroke_color="#98FB98"/>

5.style类似的使用方式

style中不要加入"app:", 直接写属性名即可

    <style name="bg">
        <item name="bl_corners_radius">4dp</item>
        <item name="bl_solid_color">#E3B666</item>
        <item name="bl_stroke_color">#8c6822</item>
        <item name="bl_stroke_width">2dp</item>
    </style>
    
    <TextView
        android:layout_width="130dp"
        android:layout_height="36dp"
        android:gravity="center"
        android:text="TextView"
        android:textColor="#8c6822"
        android:textSize="20sp"
        style="@style/bg"/>
    
6.设置drawableLeft
![](https://user-gold-cdn.xitu.io/2019/1/28/168927fe5146b80d?w=261&h=82&f=gif&s=27162)

        <Button
            android:id="@+id/btn_like"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="5dp"
            app:bl_position="left"
            android:background="@null"
            android:text="点赞+1"
            app:bl_pressed_drawable="@drawable/circle_like_pressed"
            app:bl_unPressed_drawable="@drawable/circle_like_normal" />

        <Button
            android:id="@+id/btn_like2"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="5dp"
            app:bl_position="left"
            android:background="@null"
            android:text="未点赞"
            app:bl_selected_textColor="#fbdc4a"
            app:bl_unSelected_textColor="@android:color/black"
            app:bl_selected_drawable="@drawable/circle_like_pressed"
            app:bl_unSelected_drawable="@drawable/circle_like_normal" />

7.设置帧动画
![](https://raw.githubusercontent.com/JavaNoober/BackgroundLibrary/master/test/anim.gif)

        <View
            android:id="@+id/v_anim"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:bl_oneshot="false"
            app:bl_duration="50"
            app:bl_anim_auto_start="true"
            app:bl_frame_drawable_item0="@drawable/img00"
            app:bl_frame_drawable_item1="@drawable/img01"
            app:bl_frame_drawable_item2="@drawable/img02"
            app:bl_frame_drawable_item3="@drawable/img03"
            app:bl_frame_drawable_item4="@drawable/img04"
            app:bl_frame_drawable_item5="@drawable/img05"
            app:bl_frame_drawable_item6="@drawable/img06"
            app:bl_frame_drawable_item7="@drawable/img07"
            app:bl_frame_drawable_item8="@drawable/img08"
            app:bl_frame_drawable_item9="@drawable/img09"
            app:bl_frame_drawable_item10="@drawable/img10"
            app:bl_frame_drawable_item11="@drawable/img11"
            app:bl_frame_drawable_item12="@drawable/img12"
            app:bl_frame_drawable_item13="@drawable/img13"
            app:bl_frame_drawable_item14="@drawable/img14"/>
    
    

## 简单的性能测试
我用原生的方法写了例子里面的shape和selector，做了一个比较简单的测试：

下面两幅图，是没有background时打开MainActivity界面的启动时间和打开ListActivity的启动时间
![](https://user-gold-cdn.xitu.io/2018/9/14/165d7f8a8fb3a46f?w=748&h=88&f=png&s=31994)
![](https://user-gold-cdn.xitu.io/2018/9/14/165d7f8a8f8a5c0a?w=865&h=95&f=png&s=37982)

下面两幅图，是使用原生drawable.xml去设置background时打开MainActivity界面的启动时间和打开ListActivity的启动时间
![](https://user-gold-cdn.xitu.io/2018/9/14/165d7f8a8fbe7716?w=823&h=92&f=png&s=38142)
![](https://user-gold-cdn.xitu.io/2018/9/14/165d7f8a8f96f979?w=786&h=75&f=png&s=35403)

最后这两幅图，是使用自定义标签去设置background时打开MainActivity界面的启动时间和打开ListActivity的启动时间
![](https://user-gold-cdn.xitu.io/2018/9/14/165d7f8a8fabb671?w=1013&h=74&f=png&s=45823)
![](https://user-gold-cdn.xitu.io/2018/9/14/165d7f8a8fc5ddeb?w=747&h=125&f=png&s=40663)

可以看得出来，其实通过自定义标签去创建drawable并没有真的很损耗性能，其实与本身的drawable.xml差不多，但是在list中，如果没有使用viewholer，会比较消耗性能，如果使用的话，应该差不多。  
不过这个分析比较简单，只能看个大概，给担心性能的同学心里可以有个底。

## 如何预览  

![](https://user-gold-cdn.xitu.io/2019/2/15/168ef34a68818a5e?w=981&h=524&f=gif&s=3400624)  

1、如果需要对View进行预览，直接把原来的View换成框架内对应的BLView即可，即可展示预览效果，如果不需要预览可以直接忽略这些用于预览的自定义View；  
2、如果没有效果，make project一下即可；  
3、如果BLView中没有对应的需要预览的View，可以很简单的自己实现一下，以BLTextView为例：

    public class BLTextView extends AppCompatTextView {
        public BLTextView(Context context) {
            super(context);
        }
    
        public BLTextView(Context context, AttributeSet attrs) {
            super(context, attrs);
            init(context, attrs);
        }
    
        public BLTextView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init(context, attrs);
        }
    
        private void init(Context context, AttributeSet attrs){
            BackgroundFactory.setViewBackground(context, attrs, this);
        }
    }
    
继承所需要预览的View，然后在构造函数中添加BackgroundFactory.setViewBackground(context, attrs, this)方法即可。    
**注意**：
从1.4.4版本开始没有限制，
在1.4.4版本之前，
为了提高性能，这些View在编译的时候会**自动替换为对应原生的View**，所以除了再xml中，不要在代码中出现任何的BLTextView,否则会报类似如下的错误：  
    
    //错误
    BLTextView button = findViewById(R.id.text);
    //正确
    BLTextView button = findViewById(R.id.text);


    Caused by: java.lang.ClassCastException: android.support.v7.widget.AppCompatTextView cannot be cast to com.noober.background.view.BLTextView
    

## ProGuard

1.4.3开始无需添加

    -dontwarn com.noober.background.**
    -keep public class com.noober.background.** {*;}

## 使用注意
1、selector的相关属性，如果传入的drawable不是颜色的资源，会覆盖掉shape设置的属性  
2、在根布局添加
    
        tools:ignore="MissingPrefix"
  可以防止报红  
3、因为layoutInflater限制了只能有一个factory，如果有其他库已经使用了setFactory方法，比如换肤的库，只需要在其他库调用layoutInflater.setFactory之后调用

    BackgroundLibrary.inject2(context);
    
这样其他的库与本库同样可以生效。  
4、selector一个item表示多个属性，暂时无法实现，如下：

     <item android:state_pressed="true" android:state_focused="true"
        android:drawable="@drawable/button_pressed" />
        
因为无法用一个属性去表示两种状态，有思路的同学可以告诉我  
5、fragment使用无需任何处理，其Activity调用inject即可  
6、listView，recyclerView使用也无需任何处理。
如果不生效，只需要
    
    //在调用inflate只需调用一次context，保证adapter创建View时传入的是同一个context即可
    BackgroundLibrary.inject(context);
    
    View item = LayoutInflater.from(context).inflate(xxx)
    
7、自定义View中调用了inflate，同listView一样处理即可  
8、关于水波纹，如果光设置下面两个属性是无效的，必须还要有个填充颜色的属性，原因如下：

    app:ripple_color="#71C671"
    app:ripple_enable="true"

水波纹需要设置一个默认背景颜色，也就是填充颜色。比如app:solid_color 或者app:unPressed_drawable 或者app:unFocussed_drawable等都行，这些都是默认等背景颜色。而app:ripple_color是波纹的颜色。如果没有一个背景色，这个波纹颜色是无法显示的  
9、由于Android源码的原因，如果是通过代码生成有点击状态drawable，或者下列属性需要先调用一下

     view.setClickable(true);
否则点击状态可能不生效

| 名称 | 类型 |备注|
|---|---|---|
|bl_checkable_stroke_color| color| 边框状态的属性，如果在sdk21以下，会没有效果，默认固定边框色取stroke_color的值|
|bl_checked_stroke_color| color| |
|bl_enabled_stroke_color| color| |
|bl_selected_stroke_color| color| |
|bl_pressed_stroke_color| color| |
|bl_focused_stroke_color| color| |
|bl_unCheckable_stroke_color| color| |
|bl_unChecked_stroke_color| color| |
|bl_unEnabled_stroke_color| color| |
|bl_unSelected_stroke_color| color| |
|bl_unPressed_stroke_color| color| |
|bl_unFocused_stroke_color| color| |
|bl_checkable_solid_color| color| 填充颜色的属性，如果在sdk21以下，会没有效果，默认固定边框色取solid_color的值|
|bl_checked_solid_color| color| |
|bl_enabled_solid_color| color| |
|bl_selected_solid_color| color| |
|bl_pressed_solid_color| color| |
|bl_focused_solid_color| color| |
|bl_unCheckable_solid_color| color| |
|bl_unChecked_solid_color| color| |
|bl_unEnabled_solid_color| color| |
|bl_unSelected_solid_color| color| |
|bl_unPressed_solid_color| color| |
|bl_unFocused_solid_color| color| |

## 代码提示解决方案
配置Live Templates步骤:  
以Android studio3.2为例:  
mac:进入目录MacintoshHD\user\xxx\Library\Preferences\AndroidStudio3.2\templates  
windows:进入目录C:\Users\XXX\.AndroidStudio3.2\config\templates  
如果templates不存在，手动创建文件夹即可；
在templates下面加入文件[BackgroundLibrary.xml](https://raw.githubusercontent.com/JavaNoober/BackgroundLibrary/master/BackgroundLibrary.xml)
重启as即可。

效果：  
![](https://raw.githubusercontent.com/JavaNoober/BackgroundLibrary/master/test/step5.png)  

如果有什么问题，方便大家交流，创建了一个qq群，群号887686934，欢迎大家加入  

![](https://user-gold-cdn.xitu.io/2018/11/22/1673a789b58ca9a6?w=10&h=10&f=png&s=94884)

11、bl_position 不能与bl_ripple_enable同时使用


       Apache License
       
       Copyright [2018] [javakepp]

       Licensed under the Apache License, Version 2.0 (the "License");
       you may not use this file except in compliance with the License.
       You may obtain a copy of the License at

           http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing, software
       distributed under the License is distributed on an "AS IS" BASIS,
       WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
       See the License for the specific language governing permissions and
       limitations under the License.

