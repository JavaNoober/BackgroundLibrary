# BackgroudLibrary
A framework for directly generating shape through Tags, no need to write shape.xml again（通过标签直接生成shape，无需再写shape.xml）  


![](https://user-gold-cdn.xitu.io/2018/9/11/165c6e5c0cff0548?w=681&h=233&f=png&s=31240)


[English.md](https://github.com/JavaNoober/BackgroundLibrary/blob/master/README-EN.md)

依赖方式：

    implementation "com.android.support:appcompat-v7:$supportVersion"
    implementation 'com.noober.background:core:1.4.6'

## 使用文档

具体使用方法以及属性内容请查看[wiki](https://github.com/JavaNoober/BackgroundLibrary/wiki)
## 示例效果

![](https://raw.githubusercontent.com/JavaNoober/BackgroundLibrary/master/test/show.gif)

使用效果完全和原生shape 
selector一样，但是只需要直接在xml中加入属性即可，例如

        <TextView
            android:id="@+id/ttt"
            android:layout_width="130dp"
            android:layout_height="36dp"
            android:layout_marginTop="5dp"
            android:gravity="center"
            android:text="TextView"
            android:textColor="#8c6822"
            android:textSize="20sp"
            app:bl_corners_radius="4dp"
            app:bl_solid_color="#E3B666"
            app:bl_stroke_color="#8c6822"
            app:bl_stroke_dashGap="5dp"
            app:bl_stroke_dashWidth="10dp"
            app:bl_stroke_width="2dp" />

如果有什么问题，方便大家交流，创建了一个qq群，群号887686934，欢迎大家加入  

![](https://user-gold-cdn.xitu.io/2018/11/22/1673a789b58ca9a6?w=10&h=10&f=png&s=94884)

## Apache License
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

