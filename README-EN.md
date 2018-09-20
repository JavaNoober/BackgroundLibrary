# BackgroudLibrary
A framework for directly generating shape through Tags, no need to write shape.xml again（通过标签直接生成shape，无需再写shape.xml）


![](https://user-gold-cdn.xitu.io/2018/9/11/165c6e5c0cff0548?w=681&h=233&f=png&s=31240)

Add this to your app's build.gradle：

    implementation "com.android.support:appcompat-v7:$supportVersion"
    implementation 'com.noober.background:core:1.2.5'

 
## Example effect

![](https://user-gold-cdn.xitu.io/2018/9/12/165ce13d4c0a176f?w=286&h=606&f=gif&s=807047)

## How to use
1、Calling code before the 'super.onCreate' in BaseActivity
    
    BackgroundLibrary.inject(context);
    
2、Add the attributes directly in the layout

[Blog introduction](https://github.com/JavaNoober/BackgroundLibrary/blob/master/%E4%BD%BF%E7%94%A8%E4%BB%8B%E7%BB%8D.md)

## Custom attributes supported
The following are all custom attributes that are exactly the same as shape and selector.
### shape
All attributes of shape are supported. The naming rule is **'tag name'_'tag attribute name'**:

| name | category |
|---|---|
|shape|rectangle、oval、line、ring(not support yet)|
|solid_color|color|
|corners_radius|dimension|
|corners_bottomLeftRadius|dimension|
|corners_bottomRightRadius|dimension|
|corners_topLeftRadius|dimension|
|corners_topRightRadius|dimension|
|gradient_angle|integer|
|gradient_centerX|float|
|gradient_centerY|float|
|gradient_centerColor|color|
|gradient_endColor|color|
|gradient_startColor|color|
|gradient_gradientRadius|dimension|
|gradient_type|linear、radial、sweep|
|gradient_useLevel|boolean|
|size_width|dimension|
|size_height|dimension|
|stroke_width|dimension|
|stroke_color|color|
|stroke_dashWidth|dimension|
|stroke_dashGap|dimension|

### selector
All attributes of selector are supported：

| name | category |
|---|---|
|checkable_drawable|color、reference|
|checked_drawable|color、reference|
|enabled_drawable|color、reference|
|selected_drawable|color、reference|
|pressed_drawable|color、reference|
|focused_drawable|color、reference|
|focused_hovered|color、reference|
|focused_activated|color、reference|
|unCheckable_drawable|color、reference|
|unChecked_drawable|color、reference|
|unEnabled_drawable|color、reference|
|unSelected_drawable|color、reference|
|unPressed_drawable|color、reference|
|unFocused_drawable|color、reference|
|unFocused_hovered|color、reference|
|unFocused_activated|color、reference|

### other
| name | category |remark|
|---|---|---|
|ripple_enable|boolean|ensure that the ripple effect is enabled|
|ripple_color|color|the color of ripple|


## example

1.stroke+corner+solid

    <TextView
        android:layout_width="130dp"
        android:layout_height="36dp"
        android:gravity="center"
        android:text="TextView"
        android:textColor="#8c6822"
        android:textSize="20sp"
        app:corners_radius="4dp"
        app:solid_color="#E3B666"
        app:stroke_color="#8c6822"
        app:stroke_width="2dp" />
equals to

    <shape xmlns:android="http://schemas.android.com/apk/res/android">
        <corners android:radius="2dp"/>
        <solid android:color="#E3B666"/>
        <stroke android:color="#E3B666" android:width="2dp"/>
    </shape>
    
2、gradient

    <shape xmlns:android="http://schemas.android.com/apk/res/android">
        <corners android:radius="2dp"/>
        <gradient android:angle="0" 
                  android:startColor="#63B8FF"
                  android:endColor="#4F94CD"/>
    </shape>
    
equals to

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
        app:corners_radius="2dp"
        app:gradient_angle="0"
        app:gradient_endColor="#4F94CD"
        app:gradient_startColor="#63B8FF" />
    
 the effect of click

![](https://user-gold-cdn.xitu.io/2018/9/12/165ce0e7226b6e05?w=264&h=68&f=gif&s=293851)

the first：

    android:layout_width="20dp"
    android:layout_height="20dp"
    android:layout_marginTop="5dp"
    app:pressed_drawable="@drawable/circle_like_pressed"
    app:unPressed_drawable="@drawable/circle_like_normal" />
    
equals:  

    <selector xmlns:android="http://schemas.android.com/apk/res/android">
        <item android:state_pressed="true"
            android:drawable="@drawable/circle_like_pressed" />
        <item android:state_pressed="false"
            android:drawable="@drawable/circle_like_normal" />
    </selector>
    
    
the second：

    <Button
            android:layout_width="300dp"
            android:layout_height="50dp"
            android:layout_marginTop="5dp"
            android:gravity="center"
            android:padding="0dp"
            android:text="有波纹触摸反馈的按钮"
            android:textColor="@android:color/white"
            android:textSize="20sp"
            app:corners_radius="20dp"
            app:pressed_drawable="#71C671"
            app:ripple_color="#71C671"
            app:ripple_enable="true"
            app:stroke_color="#8c6822"
            app:stroke_width="2dp"
            app:unPressed_drawable="#7CFC00" />
            
In fact, it is basically the same as shape and selector.  

3.Click text discoloration  
![](https://user-gold-cdn.xitu.io/2018/9/19/165f131f7e85b1e7?w=289&h=61&f=gif&s=8828)  

    <Button
        android:layout_width="300dp"
        android:layout_height="50dp"
        android:layout_marginTop="5dp"
        android:gravity="center"
        android:padding="0dp"
        android:text="点击文字变色"
        app:pressed_textColor="#919DAF"
        app:unPressed_textColor="@android:color/holo_red_dark"/>
## Use attention
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