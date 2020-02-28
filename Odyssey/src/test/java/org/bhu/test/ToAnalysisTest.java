package org.bhu.test;

import org.bhu.commons.lang.analyzer.util.TokenizeUtils;

public class ToAnalysisTest {


	public static void main(String[] args) {
			String[] inPut = {
					"某种、某年、某月、本次、本期、本届、各类、各种、各项、那幅、那种",
					"６月１４日6月14日两个半月的MH370布斯克茨,王国强、高峰、汪洋、张朝阳、韩寒、小四大前天一早核心提示： 5月16号出版的13日《人民日报》",
					"上个世纪末本世纪初期巴黎和会在巴黎举行",
					"工作者工作了常用词汇《爸爸回来了》dgsd爸爸回来了",
					"真是a1。1.5亿 中情局","：SN022，距马航 MH370失联已有8个多月。维生素abc",
					"小时候村主任，大多数高三家长都有着和她一样的想法，经过协商，2013年12月30日，多名家长联名来到学校，要求恢复补课。“我们来了40多位家长，在门口保安不让进去，通过协商，进去3位家长代表与校长会面。但校长拿出文件说，今年禁止补课是全省统一规定，学校也没有办法。”",
					
					"璀璨的烟火和悠扬的钟声度过2013年最后一个不眠夜",
					"林俊杰学习习近平等的先进事迹",
	"Hello word是每个程序员必经之路",
					"我们是排坛公主诸韵颖，我的拦网可以让朱婷之流找不到北，我的发球可以个个追周苏红之流的胸，之到她崩溃，我的传球更是化腐朽为神奇，任何不到位的球，在我手上都会到位 ",
					"奥巴马就算来了中国也肯定不会去你南阳",
					"哎哟，请问东部下层饥民和蜗居里的住户有多少是西部过来的？比例不低吧，以前去西部旅游，有时候时间比较多坐火车慢慢晃回来的时候，候车室里那整片整片的外出务工的难道都是东部跑过去然后打算回家的？ ",
					"没有张明何来平等李斯朱丽亚本报记者 桂杰 朱丽亚",
					"现代吉普李斯张飞跑啊跑奥巴马养的一条狗叫：安倍晋三 ，种渝光盘行动，百分之五，1亿5千多万，1997年东森s台36.80／ 商品促銷價：18.40／ 起止日期：2012.10.26,百分之五",
					"G字头邓颖超生前使用过的物品，包月费用不难看出，", 
					"希望郑洁健康",
					"罗纳尔多讨论邓小平理论安倍晋三阻碍俄罗斯向叙利亚出口武器。俄罗斯习近平张明的工资是1.5亿,王楠只有19岁，西门庆是美国人。",
					
					"独立自主和平等互利的原则",
					"龙塘坝乡的乡长是谁啊？",
					" 丰田这招实在高考验中国人民抵制日货的机会来了395",
					
					"白色红色黑色1430/限量版紫色",
					"轉發了的~就算沒有抽中",
					"保罗·J·布朗”的工资是1亿5千多万，今天开开心心的",
					
					"这两千五百字当中，除去不中听的，能够用于取名儿的就只剩下不到两千了。",
					"胡锦涛发表重要讲话 吴邦国主持 温家宝贾庆林李长春习近平李克强贺国强周永康出席",
					"提高产品质量",
					"1997年首次捧起考比伦杯时，王楠只有19岁，之后她就成为了中国女团的代名词。但在广州，王楠一个延续了10年的国乒惊人, 纪录被终结了，那就是在世乒赛女团淘汰赛必然出场。",
					
					"这个也再挂一挂，冲钻活动58/顶特价优惠中————————基佬们不来一发么！",

					"谢尔盖·伊万诺夫1日强调说，没有任何理由阻碍俄罗斯向叙利亚出口武器。",
					"中心校校长：拖延的最大原因就是缺钱",
					"微信微博传爱",
					
					"出行有效避免堵车、看病挂号足不出户、免费获得优惠折扣券……有了无线城市“市民主页”，市民只需要拿出手机登录ｓｚｉｃｉｔｙ．ｃｏｍ即可。",
					"多因素酿责任事故 众官员受严肃处理",

					
					"用双脚将法律的光辉带到村村寨寨",
					"赵扬部长从他家里要来了小张的QQ号码，用“国防绿花”的网名加了小张好友，并与之聊了起来。",

					"委员会已多次提议将叙利亚富人和掌权者作为制裁对象，并建议自12月中旬起中止往返叙利亚的航班，同时提议不将粮食、药品、天然气和电力等纳入禁运范围。",
					"中肯友好", "学生会主动完成作业",
					"美国财政部宣称，巴沙尔总统利用其核心经济顾问、现年70岁的穆罕默德·马克鲁夫洗钱并转移资金。",
					"在宫城县石卷市雄胜町临近海边，一家渔业养殖公司仍在使用中国援助日本灾区的帐篷。", "乒乓球拍卖完了",
					"6月底要交作业，30号前完成工作，3日前完成工作", "但问题是，并非所有学校都有资源选择“大而全”式的发展。",

					"工信部女干事每月经过下属科室都要亲口交代24口交换机等技术性器件的安装工作", "这里有一张椅子",
					"东方不败笑傲江湖都是好看的电视剧",
					"不装逼, 我们还是好朋友", "停电范围包括沙坪坝区的犀牛屙屎和犀牛屙屎抽水", "一只白天鹅游过来了..",
					"已结婚的和尚未结婚的青年都要进行计划生育", "这扇门的把手",  "生活水平很高",
					 "中村俊辅的工资是1.5亿","把手摔伤了",
					"总得受罪～！", "张明的工资是,1.5亿", "这个人是个好人", "南京市长江大桥",

					"提高人民生活水平。", "中外科学名著", "提高产品质量", "鞭炮声响彻夜空", "努力学习语法规则",
					"北京大学生前来应聘", "为人民服务", "请对以下数列进行求和", "为人民办公益", "他说的确实在理",
					"有意见分歧", "结合成分子时", "这事的确定不下来",
					"把手抬起来。", "学生会宣传部", "我们今天看到了张老师", "刘通向人大常委会提交书面报告",
					"你试试 不就知道了", "结婚的和尚未结婚的",
					"三个月的常用词汇七百"
			};
			TokenizeUtils tokenizer = new TokenizeUtils();
			tokenizer.initUsrDic();
			System.out.println(tokenizer.getWordNatureLine("a1维生素b6啊啊啊"));
			long length = 0L;
			long start = System.currentTimeMillis();
			for (String s : inPut) {
				length += s.getBytes().length;
//				List<Term> words = NlpAnalysis.parse(s);
//				System.out.println(tokenizer.segment(s));
				System.out.println(s);
				System.out.println(tokenizer.getWordNatureLine(s));
			}
			long elapsed = (System.currentTimeMillis() - start);
			System.out.println(String.format("time elapsed:%d, rate:%fkb/s", elapsed,
		            (length * 1.0) / 1024.0f / (elapsed * 1.0 / 1000.0f)));
		}
	
}
