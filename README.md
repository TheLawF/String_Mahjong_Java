# String_Mahjong_Java
全字符麻将游戏，但是是Java和窗口图形化界面版。   

我一直在寻找Java版本的麻将和牌算法，因为毕竟不想重复造轮子，最后终于从[麻雀 和了判定（役の判定） アルゴリズム 最終更新日：2008/4/9](http://hp.vector.co.jp/authors/VA046927/mjscore/) 这个网站上像挖坟一样地找到了这篇博客。感谢博客的创作者——山岡忠夫さん提供的拆分形麻将算番方式。愿新时代的互联网精神指引我们的前行。   
—— F定律   

 
String mahjong game, but a Java and visualized version.    

I have been looking for the algorithm of checking the victory of mahjong game in Java version, just becuase I don't want to repeat the same teadious thing. At last, I found it on the blog with the website [麻雀 和了判定（役の判定） アルゴリズム 最終更新日：2008/4/9](http://hp.vector.co.jp/authors/VA046927/mjscore/) like digging the tomb. Thanks for the author of this blog -- 山岡忠夫さん who provided this kind of way using seperation algorithm to measure the yakus. I hope the spirit of Internet in the new ages will be there to guide our steps.

--The law F  

  
   
## 2022/7/16更新：  
去这个：[GL-MahjongTile (麻雀牌) _ フリーフォントまとめ](https://www.fontmatome.com/gl-mahjongtile/) 网站上找了一个开源的麻将字体。  
  
## 2022/8/18更新：  
· 添加了游戏主界面，现在点击“开始游戏”才能进入游戏；  
· 添加了游戏设置界面，但是目前只能设置起和番。点击主界面的“游戏设置”按钮进入设置界面，在起和番设置的下拉框中选择起和番；  
· 添加了一个用python写的.exe程序，该程序可以判断同级目录下一个名叫mahjong_data.txt的文件内的玩家手牌内存在着什么番种，并将番种追加至文件末尾；  
· 更新了和牌判断，现在要在满足了拥有四组面子和一组雀头的情况之后还需要满足起和番的数值要求；  
· 更新了结束界面，添加了“返回主页”、“重新开始”和“结束游戏“三个按钮，大体上能够正常运行。
