package com.wyx.jiebaanalysisdemo;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.WordDictionary;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootTest
class JiebaAnalysisDemoApplicationTests {

	@Test
	void contextLoads() {
	}


	@Test
	public void test() {
		JiebaSegmenter segmenter = new JiebaSegmenter();
		/*单词*/
		System.out.println(segmenter.sentenceProcess("小明硕士毕业于中国科学院计算所，后在日本京都大学深造"));
		System.out.println(segmenter.sentenceProcess("这是一个伸手不见五指的黑夜"));
		System.out.println(segmenter.sentenceProcess("我是你爸爸，你个傻逼"));
		/*多词*/
        /*String[] sentences =
                new String[] {"这是一个伸手不见五指的黑夜。我叫孙悟空，我爱北京，我爱Python和C++。", "我不喜欢日本和服。", "雷猴回归人间。",
                        "工信处女干事每月经过下属科室都要亲口交代24口交换机等技术性器件的安装工作", "结果婚的和尚未结过婚的"};
        for (String sentence : sentences) {
            System.out.println(segmenter.process(sentence, JiebaSegmenter.SegMode.INDEX).toString());
        }*/
	}

	@Test
	public void contextLoadsJeba() {
		String text = "小清新塑料管件" ;
		System.out.println( "Jeba 分词  -------------" );
		JiebaSegmenter segmenter = new JiebaSegmenter();

		System.out.println(segmenter.process( text , JiebaSegmenter.SegMode.INDEX).toString());
		System.out.println("加载自定义词库的信息，开始做自定义词库的分词------->>>>>>>>");
		// 词典路径为Resource/dicts/jieba.dict
		Path path = Paths.get(new File(getClass().getClassLoader().getResource("dicts/user.dict").getPath() ).getAbsolutePath() ) ;
		// 加载自定义的词典进词库
		WordDictionary.getInstance().loadUserDict( path ) ;
		// 重新分词
		segmenter = new JiebaSegmenter();
		System.out.println(segmenter.process( text , JiebaSegmenter.SegMode.INDEX).toString());
	}
}
