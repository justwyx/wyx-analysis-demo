package com.wyx.jiebaanalysisdemo;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.WordDictionary;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootTest
class JiebaAnalysisDemoApplicationTests {

	@Before
	public void before(){
		System.out.println("before method");
	}

	@Test
	public void testProcess() {
		// 词典路径为Resource/dicts/jieba.dict
		Path path = Paths.get(new File(getClass().getClassLoader().getResource("dicts/user.dict").getPath() ).getAbsolutePath() ) ;
		// 加载自定义的词典进词库
		WordDictionary.getInstance().loadUserDict( path ) ;

		JiebaSegmenter segmenter = new JiebaSegmenter();



		String STR = "312312312fasdfs我塑料管件来到北京清华大学北京中医医院小清新,";
		System.out.println("SegMode.INDEX: " + segmenter.process(STR, JiebaSegmenter.SegMode.INDEX));
		System.out.println("SegMode.SEARCH: " + segmenter.process(STR, JiebaSegmenter.SegMode.SEARCH));
		System.out.println("sentenceProcess: " + segmenter.sentenceProcess(STR));
	}


	@Test
	public void test() {
		JiebaSegmenter segmenter = new JiebaSegmenter();
		/*单词*/
		System.out.println(segmenter.sentenceProcess("这是一个伸手不见五指的黑夜。我叫孙悟空，我爱北京，我爱Python和C++。"));
		System.out.println(segmenter.sentenceProcess("我不喜欢日本和服"));
		System.out.println(segmenter.sentenceProcess("雷猴回归人间。"));
		System.out.println(segmenter.sentenceProcess("工信处女干事每月经过下属科室都要亲口交代24口交换机等技术性器件的安装工作"));
		System.out.println(segmenter.sentenceProcess("结果婚的和尚未结过婚的"));
		System.out.println("===============================");
		/*多词*/
        String[] sentences =
                new String[] {"这是一个伸手不见五指的黑夜。我叫孙悟空，我爱北京，我爱Python和C++。",
						"我不喜欢日本和服。",
						"雷猴回归人间。",
                        "工信处女干事每月经过下属科室都要亲口交代24口交换机等技术性器件的安装工作",
						"结果婚的和尚未结过婚的"};
        for (String sentence : sentences) {
            System.out.println(segmenter.process(sentence, JiebaSegmenter.SegMode.INDEX).toString());
        }
	}

	@Test
	public void contextLoadsJeba() {
		String text = "小清新塑料管件北京清华大学" ;
		System.out.println( "Jeba 分词  -------------" );
		JiebaSegmenter segmenter = new JiebaSegmenter();

		System.out.println(segmenter.process( text , JiebaSegmenter.SegMode.SEARCH).toString());
		System.out.println("加载自定义词库的信息，开始做自定义词库的分词------->>>>>>>>");
		// 词典路径为Resource/dicts/jieba.dict
		Path path = Paths.get(new File(getClass().getClassLoader().getResource("dicts/user.dict").getPath() ).getAbsolutePath() ) ;
		// 加载自定义的词典进词库
		WordDictionary.getInstance().loadUserDict( path ) ;
		// 重新分词
		segmenter = new JiebaSegmenter();
		System.out.println(segmenter.process( text , JiebaSegmenter.SegMode.SEARCH).toString());
	}
}
