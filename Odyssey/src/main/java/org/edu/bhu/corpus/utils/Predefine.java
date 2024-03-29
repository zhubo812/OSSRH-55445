package org.edu.bhu.corpus.utils;

import org.bhu.commons.lang.analyzer.bean.TermNature;
import org.bhu.commons.lang.analyzer.bean.TermNatures;

public class Predefine {

//	379个基础词性标记+"begin"+"end"两个位置标记共计39个标记
	public static String[] natures = 
		{"begin","v", "n",  "u", "a", "w", "t",  "m",  "q", "nt", 
		"nr", "nz", "vg", "k", "p", "f", "r", "c", "ns", "s", 
		"d", "j", "l", "b", "i", "ng", "z", "tg", "y", "nx", 
		"ag", "o", "dg", "h", "rg", "e", "mg", "yg","end"};
	
	public static String[] natruesPLUS = { "qqy", "qqm", "mq", "qq", "aqq", "aaq", "vv", "vvq", "vvo", "vyv", "vlv", "vbv", "vmv",
			"vlyv", "ia", "in", "id", "iv", "la", "ln", "ld", "ldm", "lt", "lv", "ly", "lga", "lgd", "lgv", "lgn",
			"snr", "aa", "jns", "jn", "bg", "zg", "zz", "ad", "ad", "ala", "dd", "jv" };
	
	public static final TermNatures M = new TermNatures(TermNature.M);

	public static final TermNatures MQ = new TermNatures(TermNature.MQ);

	public static final TermNatures NR = new TermNatures(TermNature.NR);

	public static final TermNatures NZ = new TermNatures(TermNature.NZ);

	public static final TermNatures NRY = new TermNatures(TermNature.NRY);

	public static final TermNatures EN = new TermNatures(TermNature.EN);

	public static final TermNatures RU = new TermNatures(TermNature.RU);

	public static final TermNatures GK = new TermNatures(TermNature.GK);

	public static final TermNatures END = new TermNatures(TermNature.END, 50610, -1);

	public static final TermNatures BEGIN = new TermNatures(TermNature.BEGIN, 50610, 0);

	public static final TermNatures NT = new TermNatures(TermNature.NT);

	public static final TermNatures NW = new TermNatures(TermNature.NW);

	public static final TermNatures T = new TermNatures(TermNature.T);

	public static final TermNatures W = new TermNatures(TermNature.W);
	
	public static final TermNatures SNR = new TermNatures(TermNature.SNR);

	public static final TermNatures SN = new TermNatures(TermNature.SN);

	public static final TermNatures NULL = new TermNatures(TermNature.NULL);
	
	public static String numberFirstChar="一二三四五六七八九十零1234567890";
	public static String numberChar = "百千万佰仟亿多点又分之";
	public static final char DI = '第';
	public static final char LIANG = '两';
	
	public static final String[] MilitaryUnits = {"班","排","连","营","团","旅","师","军","步兵师","炮兵师"};//转nt
	public static final String[] PersonBackUnits = {"先生","小姐","女士","老师","教授","主任","经理","董事长","部长"};//转nt
	public static final String[] PersonFrontUnits = {"小","老","大"};//转nt
	
	
	
	public static String chineseNameChar = "艾爱安敖傲奥巴白百柏拜班邦包薄宝保鲍暴北贝本"
	  		+ "毕碧边卞别宾伯博卜布步才采彩蔡仓苍藏操曹岑曾茶查柴昌常畅超晁巢"
	  		+ "朝潮车沉陈晨谌成承程池迟赤崇仇初储楚褚传春慈从丛崔翠达代戴丹单"
	  		+ "但淡党道德邓狄迪邸刁丁定东冬董都豆窦杜端段多鄂恩尔法凡樊范方芳"
	  		+ "房飞费丰风封冯凤奉伏扶浮符福付傅富盖甘干刚高郜戈格葛庚耿弓公宫"
	  		+ "龚巩贡勾苟辜古谷顾关官管冠光广归贵桂郭国果过哈海韩寒汉行杭好郝"
	  		+ "昊浩合何和河荷贺赫黑恒衡弘红宏洪鸿侯后厚候呼胡湖虎扈花华滑化怀"
	  		+ "环桓皇黄辉回惠火霍姬基嵇吉计纪季继冀加佳家嘉甲贾简见建剑江将姜"
	  		+ "蒋焦揭杰解介金晋靳京经荆井景敬靖静酒居鞠菊巨军君俊卡开凯阚康亢"
	  		+ "柯科可克空孔寇库蒯匡邝旷况奎来赖兰蓝郎朗劳乐勒雷冷黎礼李里理力"
	  		+ "历厉立丽励利郦栗连莲廉练良梁粱廖列林蔺凌刘留柳龙隆娄楼卢芦鲁陆"
	  		+ "鹿逯禄路栾伦罗洛骆吕律绿麻马麦满曼毛茅梅美门蒙孟梦米宓密苗妙闵"
	  		+ "敏名明缪莫墨默牟母木沐牧慕穆那纳娜乃南能尼倪年念聂宁牛钮农欧潘"
	  		+ "盘庞裴彭皮平蒲濮朴浦普戚漆亓齐祁奇千钱乾强乔钦秦琴勤青卿清庆丘"
	  		+ "邱秋裘区屈渠瞿曲权全阙冉饶仁任日戎荣容融如茹汝阮芮瑞润若萨赛桑"
	  		+ "森沙山闪善商上尚韶邵绍佘申神沈升生圣盛师诗施石时史士世侍释寿书"
	  		+ "舒束树帅双水顺司思斯松宋苏素粟隋孙索塔台邰太泰谈覃谭潭檀汤唐桃"
	  		+ "陶腾滕藤天田铁通同佟童涂屠拓宛万汪王旺望危威韦伟隗卫未位尉蔚魏"
	  		+ "温文闻问翁沃乌邬巫无吾吴伍武西希郗奚溪习席喜夏仙先鲜贤咸冼相香"
	  		+ "湘向项肖萧晓孝谢辛忻信星刑邢兴幸雄熊修宿须胥徐许续轩宣玄薛学雪"
	  		+ "寻荀雅鄢延闫严言岩阎颜彦艳晏燕扬羊阳杨洋仰养尧姚野业叶伊衣依仪"
	  		+ "宜义易奕益阴殷银尹印英应雍永勇尤由游友有於于余鱼俞虞宇羽禹玉郁"
	  		+ "喻毓元袁原源远苑月岳悦越云允运昝臧翟詹展占战湛张章长招兆赵真甄"
	  		+ "镇正郑支植智中忠钟种仲周朱珠诸竹竺祝庄卓子紫訾自宗邹祖左佐";
	
	public static String foreignNameChar="阿奥卢巴伊贝比金尔坦吉卡拉兰姆恰内奇迪"
			+ "肯汗亚克韦哲埃蒂斯多曼费莱季科博耶特里略利萨泰翁莉塔伦罗娃梅西安班伯"
			+ "卜布夫查达代丹托诺德索尼基洛杜顿楚米扬努沙林朱法戈格贡古哈赫霍波勒申"
			+ "维加江津京久库莫采佐朗雷舍马什欣良列吕蒙穆谢纳宁兹普齐乌钦茹甘桑瑟森"
			+ "武思扎保鲁乔瓦希杰图泽夏辛福约占拜贾切邦当苏包茨丁捷坎龙滕松冈哥因娜"
			+ "柳东沃南塞热赛永尤本农豪察帕高冯盖丽门恩皮唐万旺娅别海佳强宾登赞菲奈"
			+ "琳隆厄先佩凯道文康勃丝廷连通詹济富舒敦戴赖瓜奎昂罕丘缅琴绍祖汉劳施提"
			+ "容力留涅迈毛瑞根雅依策岑默昌庞彻居措焦玛麦芒温方黛芬弗得鲍堡肖孔昆明"
			+ "瑙妮纽狄宗然艾芭陶嫩曾休胡翰凡欧密丰佛烈洪若考平威歇夸让摩讷派潘芙彭"
			+ "珀柯叙日琼秋屈莎尚魏修许铁真治那延坚年圭契黑亨蓬毕廖嘉木云士卓史素路"
			+ "甫历莲易席雄孟怀钱伍汀叶兴尧珍华蔡立朔彼墨生逊珊可爱琪曹青范白柏理廉"
			+ "锡英玻大惠曲干撒培柴崔伽山圣邓额第浦斐美伏蕾葛雪缪杭何荷侯简茜萝汤开"
			+ "腊来茵乐黎李刘梭露慕茂太迭义各非薇";
	
	public static String charLine="时过境迁城乡特优生家说谎钱币多方面准确智星集并谷地同场竞技牟取学经土道拉斯穆德复检膘情藤木手气团伙处互联网络摩托车保送肖诗句雪花呢普训羽叶死者行毕总共会员费官僚资本湘桂体窝匝配合飞夺南阳村西奈山三漏皖北半封建远涉还清科研路线才消权病根晋四区收购业呆板何苇席悬置乐池监装乔之喜通电率屏幕争金银兵工厂软一门心思京桥二丁目平原县喀和顺间接按量博物馆界商品属岩丛贡扇罚则笔端惊魂未定楚河大街饱沁水磕头桌这来相命摊观览晚高座坑底了然于胸位键茂兰试穿纪付预隐蔽型警报俄鸽镇数迷发丝不苟上坪管理申童服捉拿归案信息库易推委冲绳番禺人言传例文球看望起拍价万有引力宿礼宾司印斜轻机枪授职炒宵禁宣泄红烧海鸥脚法陈因现实主义外虚构周沟弃婴宝尤为娓种社坠滑尼玛先遣组笼里豪中健A名铅白眼弄作假在精对苯甲酸下降调骂黑年月日教所沾染足够笑语声喧真东寺两用升缓肉制享受性涨跌幅草督廉洁犯困秤杆味米纳活动室胶布治得格达峰伏疾书嘴啃泥展化开式巨被采访排碱誊写悟加演休止烛台螺钉胳膊屋坡林荫箱形濮令冰华宅羊皮纸肤浅初光焰值班腔势血瘤小号频繁绍兴舀变致踪迹表期富裕深茶店首度离去闪绝版安全带以浑天成悦济废弛苏伊公漫要冷热景出粉籍荣幸侨汇倒影园附丽导与奴役忧贸部风习险统流蛘老耦浪待续择善而咂滇嘉厦弦指示旅游世航汉市吮尔故居男女束阁衡断裂如自尚政爆随波逐叫好参塔架泉涤荡坎静药宁音澄执湖无可辩驳近许屯纾解民历史塞内荦潜哈藻糖革竹帽院康征议身肥王支向敌明攀枝欧洲造弱八连靠吃干榨净十鱼腹讲赞颂环感凄切割抓站负责每况愈百态纷呈条进攻沉厚逊克勤防火罩的是祝愿修款圆规前什若瓦朗诵桦川终房队分蓄洪冶美术就坐碎步核验灯象琼玉赶长叹倾尽桑芽儿桐专爵士玩耍点击赃护膜列颠群岛交埠臂祸从愉快阴挑错份追认走亲友马蹄胡乱古柏色领夹云播钦佩莫剧针昆仑容启企宜锯孤立运唱恳炊劲笈穗闻江太话考勃登堡州甘井子细徽菜当强直弓箭夏恰帕意俚俗武屎壳郎奏尘及违章拥盐都刑别占停熠彩闭垭口朦胧涧跨完稽打蛇眉毛转贷单标眠夜冒烟永胃癌夫左膀右昌盛津仁遍减怕节剂阀溯至挂探测器喷洒折庄私滥挖类党新催讨刻字卫莱索酒积劳国歧视垦伸缩最低灼帮贫恐绿塘边镜我峡知识疴后冬重伪扎疫抽槽械关事襄辍论阿秋季混沌损害驰邓买冠零敲蒙巴厘虎篷睡衣急奥几亚姑项题石洞沙扶辅读办补比盘坚戎毒序跋娱投放尸骨卢韵浮颈泾源黄缥缈亘神紫蓝撒样丹轮慎邵个丰师粥满春娃租户圣姿盗码怪苦难麦角承雕塑刀失察罗利贱获便纲筹欢牵冀豫固继练密植胰第孩务见旦畏途益券记簿票志鸵鸟菱聚契约千回果张俯贻非籁俱寂窗煤灶代段餐改任状涅始敬窑劣聘龙滴浸牢订鳞伤燃吸寄存储免傅朱籽棉漂刚贤抚慰田履卷末亭守嵊溪培养少五曾由衷锰背肯辛顿宫速岭产庆谊救灾鳌扳妄墓葬堰勒营晴茬鹅族余坛粮莲举央战问雌鸿选粗暴飘凿吹拜努惹臭虫词蛋质福功宽鞋冤牙破油兼额税葫芦换胴决偷窥扣T恤寸九悔等舱局微只维某级鸡讯椒掌饮蠢脱钩苗烘寡促销签结晶钓贯稿辣毡包恩省粘膝效笺惜败黯召樱芭蕾舞皇朝槐昏歇厅威靡鄙落滔罪温创缎赛鸦反哺筒铁傲慢厩倍署灌腰育曲隋唐蓊郁焦须臾毁吉汤隅孙紧炎症妇潮依异客邮硫疗钟痰墨祥阵双均匀侯轫船贵股舢范棚谛禾留汪溜青葱基奢侈泡缘跷输菌此算售秀允妈搬简犁桧仓蝶摄计证持恶珠残谈巷怀壮凉柜廊府烈陵蜡具浩课画短延请映掉吱甬砝苑畔散齐咖啡渍耗瑞借措茨喊迦顶告勾肩搭旗侣系际材灞滩锐擎枫渐划入仪振寿忘吐艳滞轧荒芜魔爪堑港审姣欠贩嗟捷财楼肆赴压楠澳甄拔谢韩军袋极哨薯糊涂麻龟钻袁岗弟剐删仲陆盖押校既喇嘛灵六畜食鼎据涟正钢琴伯萨恃查嘎秧律插顾野译给予卉冻雨墙瓷七姨砂胪湾货褪绕备醉梦鲜糠该舍裁元艺腌鸭瓶棠空披戴蹲更层励图没侠芨奇素硬射滚烫求伦片蒋搞庭今晨乏控卡鲁厄蓖拙增抢厨妥次叭响瞻峻苍鹰英模艮众碧常派围梅锥藏痛欲撮箕奋斋姻铺瓮驶扰提伞卟啉脑潦甚松再阔御驹歌畿乙肝爷广柠吞荷晓栏圈医卜楞烦踞程垠梁塌陷逃曼旱葆郭洼严柳羞骥迟钝棋汗整需锦添粤遁稚缸娩雅卑岸注霜露缆骄淫逸谨秘喃杨香设援鼠辈泰衩虞汽翻绑综寻捣仞饰夸幼除夕堂邪痢胜亡庸绒助薄雾傧伴奖幻牧填筑伽良洗森剩脏辞拐弯照烽燧孝献磁闲浴赁褐各撼农虹迎判醮纱锭擦究竟廷其臀穴棒脉蹶透适醒超诸乌爱鼻岳使爬谆莨菪喉久述珍碳岁协暹粒冕孜概弧纤债敖淖烂仔臆遗像尾椎鞍操纵刊驱翅杜垂泪泵肃狩猎昂扬嗓趋奉媚罐牌巫婆卖啄泣临谱搜纺织惠坝熟谙圭钗圪戏础颐洋滨缚尝豆勘垫嘻勉8墟巅烯扪濒啥铜诉胆遥震抄父母撤退耳他袜擂鼓墩鸢沈着融纯鹭侗彻赖账悠副渔偿坦杀锏略斑雷斩截脂但灿磨疮疤盟庐竿彭饭想昨拇糕诺称含撞葡萄廓雄挺替殖料菊痴愚旋舌柴聂堪拼搏舅避脸孔录饶遵疏宛返涯潭轰炸凶钎析磅绰早诊脐盒凝栅殿限橡筋猛树葛杯鉴晦暖域拟翁柱暇雁敷逆径栖敛你掷窖缰蔬铩艰卓孕典丢咬妒嫉呱貌篱笆祖矿拖猿蝾螈扭顽斤靖腺做祈姐妹挟趿菩酿档昭凹磺擅亮载埃烹勇巧唠嗑纰缪臣骤昧扔弹矛盾鬼胎到斗饥浠蜀桩谬邦銮瓜绥芬芳晒独楣赔纹陶佛芒禽钙毫贿粪递幌乒乓戒候显骗淀猴蝗莺滦丸淘件将龄苞杉乘怜驻策慈氧奶宪赜杭往OK眚障猜刷斐拳编著诋俭憨铝移湿疯钳陪危茵盼锅罢渗嬉块浓桶戈拓扑衬批坯暗牛咸悼唁蘑菇瘾敞绵舔砖叉般炕遮乾挥肺峨乞鹿耕稀释液吵瓣197·丑挠鞭嗡擒锣赤仿焉疲惫枣榷炉听润锡必缺毋刹杰舒猕桃横轴鞠躬沃丈媳胞掠30否稃丘檀浦僵鄱疑牡摘腑坨腐朵仰儒篮椰汁韧壁舾寒峭陇傻鸣绩掩欹坏卒招泼袭噘棺杂硕缉捕佚芸尊那懂锂摆淋抹挡铢盲唑哲挫惨痕阶叵膛吻琮亏灰椋躲锋窍崽怎么霞翠贝差肠揭迫睫囿阅循宋懒宗谅兔矩污壶详旬哭掘渡刺涡狗蚌楔仗些念越扩拨穷棱唤襟吊觉绘敢屠宰翼渴估蟊贼陡屿诞EV恍隔蚀B噙椅跆垄鹏闸窃阜煮沸炮翔尿靶尖弊咳艇枢纽床篓胼胝评涛铃另泊涮嫩庇刍昔溏佳渺婚匹挣劭黎茎秆侦辱羸堆洛钾绸帝酥介抑璜樟蔚愁晕垣逼仄锤贴兄郴阖偎魄突硝崖羹饲篝旧缝柬埔寨桤忙疆届巾粟辉煌豁鲤苔挨凡拒肌倨浆敦刮煎熬狐IC抗舆酰讹诈页奔怒吼啸赏鬲痹淤枯逢把袅辑雇庙茫拦蓟逗胺袒隆橄榄柚赦宇泛舟亨埋崩溃串套狂剑她俩碟撰挤旁耙耱锱较狮贺应螃蟹祛己匾殊钮屈渠誓炯已悖供巩盈躁栋刁隶误娘霍郡凯弥丧愧拱萦碗氢链霉驴衅盆劫惮袂眷忆啊犬燥怡乖僻淡妖盔柔淌衔滏饺褒樯桅迥饼锹邯郸匠衍姗袤艾耶腿裤褂躯迪炭渤叟欺践握描摹淮杞漆砍馅酯贾赫驾住稻姊篇麓嘶李灭蒲迸栗累菏泽劝涝踏畸鸫浙祜禄妃霸茴劈裆戊婉衢柞焚郊蚁楹偏ⅡpH午乍湍瑕禀祠噪妙嫡酱伍纂摧鸳鸯冗曳沽镍疼蝉赣唇谣哪梨樵沱鞘倡猪也剔缴绛哑亟噬鲨琢浇沪饿叩洄哆嗦栈狼抛却蛾寅寝肽梭F5哥傣塬掳患脊扒册能遏蜂蚕豌桔栓似蒸或椴旺刘熄捏茔燕窘偃辨携畅嫂挪绶玻骛牺牲靳诬谄垮凤抖诚呼啦姚黔壤睹暑巡裱脯釉骑剡愣摇S泻俺摔悉堤潘凳冈赵坊捐沂兀宴腊匪址椿矸沿圳淹垸帷恪侵嫁撕铲稠答菖拣澧奂匡遭町姓矮潞潢幽默炼厉赌遂甸惯胭忠恋绅彰闵衰佤巢绣逻惭纠酬嚏坷悍徒垃圾凰伶找帐颜耸—尺猝碑瞩罹迄玄又跳跃狱俑痒泗剥殍怯慕哀麒麟噶挈邀禅吾缕舶睦徐侄榆陋乃馨闾勋锃轨兮苛秒即柑橘洌诛伐袖兽黏扫壅绞篙秉鹞耀夷迈希撬帆诙谐摸谋耄耋耐鹊帅驿吓啤迅倚寇焕梢们狸辐孑谶纬忌讳凋喻暮霭窜呀韬乎让镀耿晰秦丙烷锶疣畈愤蓬齿缄惟砸醇吴淞夯拆耻噢矶腾喝枚菲驼雍滤鲶竭凌蛎戟跑磷慌滆聪颖粱吟嫌吏檐翘抡斧涩睿簇爽傀儡晏迭帜旨慵谚卵MW虐甜笋镁惦朴妍沆瀣湟惚浔触檄黍邃君疹稔激揽妓铮凭牍硼芝醺犟灸搓澡辽括蛮捞膳帘悲梯敏叔薪叠唯葳蕤恕很馈渣颅嶙峋蜷绷骖嘘苹窟翡觑荔闺邑垒檬莽汕胖唾戛涌妆沧蔗绢笃琐龛姆氏逮茅戚圩咨询恒屡慧髻鄜噎泅株糟憾眸凑哄羁浏叙撷暨侍诧庞鳖凛冽仅掣魁伟惩懦昼翎枰佃抨秸泳磴斥堵寓袍逝狭邋蹋弈翰蹦钞瞌荆猫佑酋硅陕潍烙廖庚誉噩阻遇笛矢咄烬窠沛湮叽喳恢宏鏖侧虔藕裳贬歪酷暂扁忽仙淳熙棍锚郑4虑揪唉铸裙佐鄂榕辗砼聊捂匣鹦鹉淼姜摁尧脆琉咕咚榭沐辇焊梵蒂擢顷皑渊宠赊蕴喘甫苌崎岖瘁巍赚熔弋担彷徨捡拾腻锁娇丫漱毯2符咒恸湎惑捭邱杏棘荧琅琊镐沥蹈羌扯骈娄赎臬慑骚闯敕逾柘稳瞿寐攸赠跫砌抱芯佣擀杖辙抵辆孺皆鲑氓奎傍蹇柯坂豚鲫哉奸婺踝贮霓莘芡阎囤X辫缜诡谲肋倔卦董屑葵槌氆氇朋恼爹扮筵讽鲸锌吆馋唆氰囊涵痈疽拭『垢谦跤趸贲勺乳馒蝎莴檩拌祁刨瞳矫虽犹荞蜜茏距咀碾腕馍绪篁洽碓竖癣舰镉孵冢斟酌蓥毂溶毙沼缔豹锑G涕沦埂谒瞬辰矜诀悱厌篆岿茄泌筏枭欣魏胯漓粹哽咽靛寥焐慨砚酶菠萝仇飙讴糯抬轿馏瞥缤撑邻藉渎瘦柿怄褶钵溅秃肚阪榴叮蝴慷岂漠旷雹隧嫦娥崇殃歼碌屁蔡锷浊虾框煞肿充孟崮闽媪榜聋珊瑚忍焖蹙偶啪璀璨茧瞎蚊蝇亵殷纣鹤跟弗玫瑰腈纶伺扼施嘿渝脖俏盯螟烃忻氨肢瀚腥蛀怨蚤贪颔挎尉兆笠蒜媒骋谕璃闹旮旯妻滋瑶稗楫饧蕻颌剿缮诨汰雀吠颤兢讼札趟膏芙蓉獐钥匙汴覆芋睁滁玷枉呐俦厢蜮爸塍赝舻舳鄞祷趣掖碘诏6邸拂筐泓茭汛槟镑咪衫酣恬澜熏沫汾狙彼阂潼杠萌憷麋菅嘲曹脓剪嚷栩挽氟肄腓挝簧惧醛鬓哮盎碍阙撅峪浜谓珙诿臃倏肾礁愎酽缠淄镂脍炙夭”萎蛟珀蘖峥嵘仍豢柄晾溟瞧矽觐郧赋憎璧庠坳竣芥挚兑汶卿怂疙瘩秭裸捆溢蔓Ⅰ崭黢砥砺呕伢郇叛烤沓卧吕珲吭颇竺蔸遛陀喑俊嚣啧兜kbs耷猩犀蜚盹枧逡屉函厕坟肴蒿宕蔷薇镖嗲绮佬怖斓笞丐腴檗栽诱呗箫埙瘫渌鳅荚吡缭鱿蟠婪N颓拘蹭筷蛙偕匍匐驮戥奠妨闷尹陌圃骇蹬韦梗酪鲢踬赡戳娟啼兹沅岔桉勿蠕邢汊绊凸梳衙翩渑幄烁逛亿殉惘膺削棣捅龋遐迩祭徙蹊寰跪岐酊喂亥岚煦姬瞄睇砾瞅呛觅絮倪倘渚胀旌绺癫肘咫悸楷吝忡桕糙怔钴搂邳您峦嶂淑篡.蹴湛邂逅亩缅蔺泯酉漯怏圻诣熊卸皱窦悄耽藩瞪脾镳呜畦踉跄莓骅罕隘槎吨诟妪翱疝磬辖靴抒囚洑讦赙橇绾咯晌攘疚邡茸谀轩募潇羡镰杳痞胫踩嘟哝沮剖贞芹晤裔邛崃侮诌胱簪錾捋忖瞑咛雏锈陨嗔鬣缀蛊阕它鬻赘旖旎蔑踢啰舴艋茹Y掺髫噼掏轼泸魇婿剁麸迢炽摞帙寮捎罂嘧啶憬嘱刃谥邕唧鹨矗梧逞筛憋酵惰狡黠舵渥幡鸠镯攒捍穑稼糅鳇剃莆匕瞒婵遒舐犊棕榈貂艨艟庑纨绔搡磋髋谑颗恙佘R眶钼栾凼峙D讷陂讥韶俾漉哩狠戌恭罟揍勐壑熨犸儆秩僧枕忱恣恨篾岷绽铬箧邬诘璞瘴鲟葩亳皿稷苷′甩岫庵朔淆踵霹雳栎橱迂鄢稍炬馄饨臻倦徜徉爿瓯鲳谜盱眙鬃晃俟畲萤鹜荟萃嚎啕斡徇瓢罘趁朐柩蕉蠹哼歉碣喟眺婀娜佞碰泞榻霁鲈诽谤搅倩浃铱怠蓦铀鲛绡谁疵婢亦嗜栉镭塾楂钛辟壕揳譬滓诠飒揣枷邗埯毗羔铠糜碴卅庖扉嘁涪莞啬瑙臧氮杈侩拷跺辘鳏屐芰惶脘箔洮溆淅褥磊筝耧彬嫖U熹苜蓿酮籼辄蛳黟巳猬浣漪渭撂胁扈鲇朽囫囵诤弩娴磐秽阉酝冯骧纫瘟猾嘹莉锄窄蛤钬妞垧汹矣卤丞且庶弘宙瞟蛰垛濡呵祉赢瑁媾暧媲廪坍匈聒帖瘕荐殡苫骟穹搁镶匆樊骸陲趴皋锻佯呷拢裹撩雉惕呸缗塄挲恻掀娶炀簌釜岌滹冥诼枇杷砭骷髅痫鳜懊L觏拯绚踅濉梓杼怵擞鲍隙斛竽谏歹吁芗仕藐祀饬涿捧逍玲仃萍漳桓鳗谴侃礴踊铵茯苓哒拗汲膂焙懑菁莼埭棵仆绯荤剜喏憩饕餮罄伫砧怆叨孢粲皂珑哎蕊睑腱鸩滂楦彦魅髓岙铐隍翕盏钿薅扛峁舛龃龉颞宦袄箍曰驯荜埝妾哗煽芊踟蹰曝P崆峒娼侪纭酹伉俪喔邹铣嗬哟瘙赐钽缨嘀鹩瓴咎钕砻鼾姹嫣燎橙枸霖帧腆蚱蜢崔洇爝瀑棰椽琳艘舷幔拄霄捱毽黛崛瓤蛐掂栀踯躅袱瀛‘’谍哏蜃肓羚蹩忐忑皓玑眯飕亢溉汩淙襻谭铄诎骡馁in桠鸬鹚厮牒孚箩夙铷趾炔禹肇骊哌渲耆踌躇帛掇蜇铆咋殚捶怅俐紊鲲氯钠撇喙嗉蔫缶铭皎婶鸾嚼∶哇醋褴褛蜕舸榧赈鏊兖a槭窨霎汞瘠诫咏捺哂芮犷睛枋掰诲萧浍钧睐捻殇彝颊莎蟆饵儋眩匮虬莅醚囱骁晖笨赭鸷卯耒惺忪涎屹岱蚩铧筚暌蚂饽鲱茗苎吗蛛稞绉荇瀹ef阍芍疟杵赉辚筠殴靥辕衽觞郾-膑圹曙淬珞珈泔鹁炖濯溧羟蛸蜞骏邈阑幢彀蟾缬涞堕嗒佝偻荻龈谧剽郓躺篼馓萋沏藁蹂躏鹂漾癜皴啮烩碉鲅揉孳猖獗岬窒坞戍蛆祯痂芷瘪悫倌哓汝莳畹酚寞狈铿锵唏毖俘啜鞅彪懈翳霏烊昵懵麾嗵芪簸祗伎獭悻盅汨骆孪旭麇疱讪飓嗅嗽蒡咣嗷箴煨墅壬缦痔浚腼盂钒玎髯莠溺踮沤奄孰跻煲砰猗掐痣牦龌龊埘疡漩嘤咿唷睚眦蜥讶蔼踱锆粕陉眨搐靓莹禧咧昙姥臼轶肪诹痪怦椟仫轱裨阐厥倭铳郫岢碜钚辊耘掬榫阱螳螂痧暄螭膻瞰睽薰爻桫椤愫薹抉妩喽钡颏腮嫔嵌梆拮鳟煳+馔赅皙匿龇踹炫醴啾蜗殆锨钤毅湄咱驸阄叻虏笙弭鹈鹕铙煸蟑裴殓叱咤澈鹃菘颁蜿蜒赧甥蟒伀彗嵋鹄氦夔辜搦汐辏粳墒蜻蜓呓嘏砦圜氩缙徊癖钋聩蛹戕漶撵澎湃葺钨Z矬畴J肛俸裘鲷桷萁庾撸揖鹗喋腋眄鳄侏铛琚溴觚皲睬斫裾胚氤氲炳薛唢潋滟骼摈浒椭桨韭遑纛啁缱绻铤罔欤,訇跏趺琶嗖惆痍牯荏苒鄯遨髌蹒跚悚跹谪抠蹑黝膨妊娠奕膈敝帚噌樘镌忿陛茉煊苋粝蜴蚯蚓ord飨冉骀歙诰瞵孱鸶陟碛氽蜉蝣忤逭酡貉茼掸睢凇讣袈裟甏枥椁椹桢倥偬莒瑟v鲞弼皤菽槛刈嵬憔悴糌粑逶迤觥孽驭睥睨潸碚埤糸窿坤嗣坜阒鳕碡铂攫奚狍挛豕伥闱潆赳镞茁绎嗨戮荠赂蝙蝠蠡幛嗥挞绀铈℃洧悯噜捩哞槲蚬粽蛔豇畛嚓俳啭搀溽徕眈趔趄岑°轺嗯樽俎阡箪cm隽缛饪遴抻楸泱饯擘蒺藜酗愕g霆谗甭镣鳍涓哐啷鹬曈狄矾惴狰狞泠癸滕抿宥歃嗤衮襁褓哦橹妯娌铕璎傩涸蹉跎刽笪憧吧翟蕃摒桎梏猥闩蜈蚣藓蒗柒蟥偌氛鑫咆槿侥屣嬗徘拈麝溘缟鹑忏沔舫洱铟蹿闰跗搔谩诅袢鞑靼蜘迓祇谟尕铡玺揩讧痉祟颚拎恿琥糍拴俨砀濑冧镢褊鹳佼槁褚翥遽啖呻鞣髦瘘弑嗫嚅薮娆酾嘈茱萸鹮ⅱ簋喹彤箢绌棂覃馕潺茌涣珥牖嵩佻饴拧炷鲭圯涑剌垩荼笤瘢蕨肮燹墀坻唬樨鳝榛隼轾秣臊揶揄噍琦聆酞砷叼诩痊霾锲咙祺锒疃剀琵咔沭曦锢蝼瑜嗪唔揿瞠馐吩咐鸱搪馥馑镫惬钍鄄蹁拽蠖崂娑窈窕锉鹧鸪镗郏仟嘭恽馗帼粼癯α枞汀腠佟衲跛颀殄醍醐艄玳颦鳐铌瘼黧涠縻啻纡犄z诮綮戡鳃lu硒辎咝锗黜惋攥砣苕荥忒蘸觊觎淇檑萼砒厝燠蓑哧蟋蟀瘸讫孀囹圄忝廛崴娲恫桀骜痼蕲h缫篑囔搠虱尴尬蒽垆妁劾钺唳懋猊漕牾橥矍痱苣掮酩荨揎%±○咻宍戸酢関垱塚潟迳楽岘沢潴瀬甗堇萜郷垟対甑腭闫廿疳痿茆揆忮愠怍薡蕫祧稂丕峤嵴広j栄濠瀼傈僳両罾鬟垌辺洺颍箸亀悛玖乇鳢t戾幂桴乜噁亓箇邨箐酐亞痘穰仂仉帰垅仝仡仨仵咶攮羭妮伛伧伲倻佗呉佥佰佴佶聱佹佾侉侑侔萱侬俞俵囷倜傥倬倮倶虢偈壹偾僖僦僭僰儁儇鹫阋妣児鹘兕穂竜硍苴轭虺冁韪饷凊镩冱縄蒌溲冼帔笯吪凫糗荛刎刓刖垓屲斌尻刭湔刳刿鉥詹剣劁劐劓劢劬劼勍勔勖勰垴鬯埼塩滘澹麂嵯瑳匦痨歳畑胥陬埕崁曽枳湫礤谯螯卞胂琪茜卣卮旛谠硐卺厍厣厳厶叁鮰1q堠芫擿踔硎镛噹呤舎忾媛堀吒吣蓼阈呃饩呋呒呔呖弁呙呦硷呲呶哱幺気喱咦咩咭噔咇哔哕哙哜嗙哿唛唪唰啐怛啵喁琏喆喵喾嗄嗌嗍嗝嗳嗾懿嘌嘣嘞嘬噗噤噫噱噻嚯畷囝囟囡氿囧枘圉圊鲮垯圬圮坼磙坌溷坧坩埚坫坭垚垡垤垲垵埇埌埏埒埴埸埽堋堍堙堞堺堽塙塥墁墉増墚俣売郢铎槅戗夤夥夼峃崀嵙槃禆氅滝漈畠菉蔵衾豊輗辂鄣隗鰐鸨罡昊莪肟苄奁奘Ｂ満萊劵谿妗妫妲姒姘姝姫姶葑娅娈娉婷娣睩婊婧媵媸嫘嫜嫠嫫嫱嬖嬬嬲嬴嬷虛孓孛矻悌轲穀孥孬検绠喰宓墕旰宸Ｎ堨寤値岞箬鲵嵇脔尥舜尨蚴屌屙舄屦榉魈屺屾岈岍岜岣嵝槻岵岽峄闳雒崞崤崧崾嵛焘嶝嶷汙渟芎巣巯巻巽巿帏帑晗帯帱珂帻幞舘庀笫庋庥絜庳庹廑廒廨邺柙肼璋弢弪弶铗彖彘彧珮瘅徂筌荃薗徭徳徵徼忉忭菴忸怩瑾椠怃怊怙旸 怫怼怿恁恂恚恝恧瘝恵恹恺悃悒悝悭惇齑愆愍缇愦慊慝憝懔戆戢戤戬隠戽赀扃璺扦乩拊抃畀豺抟柝拚拶璇挢挹捃捌鲊捯掊掎掭掴掼掾揞揠揲揸搋搌搛搧搴搽搿摂摅摛掞羯摭摺摽撄撖撙撺擐擗擤攉歠敉敫蝥筲楮脰澙発筱篠阮於旃旄旆旒Ｋ晷魃旻昀昃昄薏苡昕昝馀昱昴昶晁晔晞晟晡萘暝暻暾曛曜曩蟮曷蚜朊朕坬镕隈杌钊逵杓蘅鳎璐箎篪杪杬杲蕈枨枵枹柁柢柰酄栃栊榱猢狲栝栟栲栳桁桄桊葚桜桡桲梃篦梼棹棼棽椐椑楗楝楢榀蠵榇榔霰馘槊様槚槠樋脇樗樾橐橚橛橿檠檫檮Ⅵ欬歆殁殂殒殛殪殳毓毪毳氐瘀氕氖氘氙氚氡氪臌虿蛭螅氷堌燊祚氹汆葭汔褟汜珧蓠汭沘獾沣沩鲀泃泐脿泖涔泫泮泷泺洎洙洚洣洩洫洵洹浄浈浉轵浞浬浯浰蝓浼缁涫鲋淝淠淦埗甽渋渫湓沚溥溱溻滍滗滠滢漤漭漷濞潲澉澌澍澥澶濂濛瀍瀵灏鳊炅箅炜炝炱烺烀烜烨焓畋焯焱焼煅煜煺熘熵燔燮爨爰鲆窓腩虻牝牤牮牿犇犋犍犒狃狎搰狒狛狯踽猃猇猞猁猡鼬猱猷猸猹払獍獒獠笄玢玥愒玮珉珏珐珩珺琍琛琨琬琯琰瑗瑛瑭璁璘璩瓒瓞瓠瓿嬛甍甓甙産睾胍甾畎蘂畚疋疔疖疠瘌疥螨疰痄痖痤痦騃瘃瘊瘗瘥瘰疬瘳瘵瘿癃癍癔癞鼋鹎鹇皈盍盥擩黒裰眇眍眢眭眵睃睏睪瞀瞋瞍瞢瞭瞽覧矧礓砉砑砘砜砟砩砬砹硇硌硖硚镪硪碇碥碲碹磉磔礅祐祓祢ｅ祼祾禊禚禳跣秕秫稆稣稲黩窬窆窭窳窸窣竦荪笊笏笕笥笮笱笳笸笾筇筘筢筮筻箜篌箦箨篚簏簖簟簠簦簾籀籴粜粞粢糁糇糈糨菀絷綦総緑繇纟纩绁绂绋绐驷绗绦绨绫绱绲虒缂缃缋缌缏缑缒缡缢缣缯缲缳缵罅罨罱罴罽獬羝羰羲羼羿翀翃翊翌翦翮鸹耔耖耜耠耢耨耩耪聃聛聿苁蔻肜肱胛肫肷胄伕胗胙胤胨胩β胲脎脒蹼脞羧脲脶腙腚腧腽肭膣膦臁郐舁舂舡舣艉艏艚艴艽糬芈艿芑芟芤芩荽芴芾苅苊苘苠苤芘苻茇茈茑茕茚菰荀薙狝蛉荑荖荩荮荸莛莜莩莰莸菟薐菹萑萩葸蒇蒈蒉蒎蒯蒴蒹醌蓇葖蓍蓐蓓蓰蔌蔟蕙蕹薜薤薨蓣藿蘩蘭紋虮蛄蚋蚍蚝蚰蚵蚶蛏蚧蜊蛩蛲蜣蜩蜱蜾蠃蝈蝌蚪蝻蝮蝰蝽螓螗螫蜍蠓蠲衄衿褡裉裎裒裥裼褙裢褫褰襞襦観觇觋觌觖觜觯訾詈読謇诂讵诃诒诓诔诖誳辔诳诶谂谇谌谖谘谝谡谫谮谰谳谵豉豳豸貊貔貅貘贰贳贶祎贽赆赇赍赓赕碕廼趑趱趵趼疐跖跞跬跸跼蹐跽踣踧踖踺蹀蹯躐躔躜軽辋轷轸轹辁轳迕逦迨迮逄逋逑逖8逯遄殣遘遢邙邝覇邰邴邶邾郄郅郗郛郜郝郦郯咈鄹酃酆酎酏酕醄暢酤酲酴醅醑醢醪醭醯醵阊鉾銎銤鋈鎌鎏鐾钆钇鼹钌铞钏钐钔钜镚钣辀钪钫钭钯钰钲钷钹铉铊铋铍铑铒铖盉铥铨铪铫铯铰铴镴铹铼铽锇锊镝锍锎锓锔锕锘锛锝锞锟锠襕锩锪锫锬锴锸锺锼锾锿镄镅镊镎镏镒镓镔镘镡镤镥镦镧镨镬镱镲瀞筭闶闼阃阆阌阏阗阚骘阼阽陔陴隰隳隹雎雠雩雫雯霈霊蚨鳉鞒鞔鞫鞴韫韮頫頴顼颉颛颥颟顸颡颢颧僝僽飑飚飏飧餍饔饫饾饤馇馊橼薷馼踘驵驺驽骐骒骓骝骞骠骢骣鲠骰骱骶髀髁髂髑蹠髡髭髹鬈鬏魑魍魉鰜鲽鰺鳔鲂鲎鲐鲒鲔鲕鲚鲣鲥鲧鲩鲰鲴鲹鲺鲻鲼鳓鲡鳘鳙鸰鸲鸸鹋鹌鹍鹛鹡鹣鹪鹱鹾嶋麈麴栌疸黉魆黪黥黹黻黼黾鼍鼐鼗鼢鼯鼷鼽齁齄龀龅龆龚龠婕妤菡嫒璠璟瑄赟昉蓁锜偲鋆焜琇珣瑀玠嫚湜琤沄剛順羅為橋瑠優語閉貞邊龍輔勝軍愛樹長徹誠純園達東篤飛鳥亙実規貴華絵紀記継呂潔謹進寛頼櫟斎輝則夢斉謙慶譲顕榮潤紗渉愼昇準鉄統馬賢嵐憲暁興勲薫艶陽義見壽伝眞備陳暠賀簡倫寧喬権識員鎮計島陣聡開澤賞幾歩蘇亜萬風時涼咲絢欽諒設葉電陰種淸館倉麗巌麿騎親組磯織駿峯鷹場農悪結緒経遙瑤揺円連蓮聖尋尭堯層冨綱岡覚雲廣國積済間紘頓會鍵軒節當錦濃幹孫洸鶴綾穣師響門與範堅講拡団養資暎護衛藝蕗顯荘樂齋車啓紳監掛遠紡績彌貢魚圓納務悳鋭遊機諭後質宮楓禮貫渓復蔭壱皐紅儀創劔鉱鑛碩穎須埜舗奧濱並嶌製醸運鐘衆弐峠柊乗鍬鮎條針調樫抜棟戦跡紺訓髪専專測鯉輪酔鐡鐵脩鷲瀧苺鴻庫鈴異籏験紙別兎檎図収栢拝鳩奨颯協財導嶽將遼詩實魯頂龜齢禰簑額詰漁湧嬢領銀滉従該職視評絹麺紹曠陸窪渦鏡駒無鴇寶側頭繪燦來惣驥楯極裏禎勢弾雋維煕業個誼綴貝淵報釣鋼靜稜鄰鵬粛仮莊漸聞階凜願揚諏訪筆湯細總兒濵冴動働傳稙夛薬槙鉢嶺鑑灘囃懐熈現飯聰燿絋蒼絡凪隊梶蕭詫辻錬夘廬椙閑沖暉偉槇爾強毬崚録挙諸軌萠藍銈鶏類渕蘆盤曻詳詮霧鮮櫻燈労詣単邉畝処恆憂諦讃粂応離銅勅艦砲湊鳳鋤億鍋賜爲衞謨塗甕揮盧舩閘毎楊詔栞鞆銃籐凱論負燁枠続歯紬榎仏髙給賣暦詞溝澪廟撫練徴嵜緋捨礒婦択偵鋸鯛隣詠鳴內傑圏訥掃鎭郞倖縁巖窯苧環漣笹藪毘賴應懸鈍轟雑廃載縦鉦張櫛氣築誌儔濟叡憐蔦繭菫鍛聲鏑釘漢邇諌纮曉審錠鴎槍鷗銕僊對楳鮫桝騨晧絃劇鋳銑栁還茲關歓鐐遷銭萢讓綺蛍査杢術庁壯賛際証臨稟汎晄學綿飾騰銘綏薩弌嚴臺諄頌韻黙鯨謝涙習籠團闘補淨説勁寬瀨錯晩談決蒔詢獅縫壷壺椛樺檜書鷺腸鋪話標筧諫塁纐纈葦攝烏錫柵壌匂鎧込袴蛯闇觀饒菓蠣執丼簗柾祿滿聴項櫨飼栂終漬覺璽難喩籾雛勧線塀彥齊駄勳淺鎗靑棲諾乕穐翫熱權釈枡鳶閥澁許療粧鱗顔猶疎確縣笘轍盃閏榊榑筈從蝦莚竪硯螢絲杣枩濤閤墾穏姉鵜網鎖鑓問籔採梠適飴楡輿鍔桟";
	
	public static String[] foreignNameMatrix = {"20323 17943 1064 779 403 110 21 3",
			"18116 7785 2016 2866 3436 1614 370 29" , 
			"6396 1711 2133 896 611 606 333 106" , 
			"14748 7862 2805 3039 926 113 2 1" , 
			"18809 4529 5560 4119 3287 1074 189 51" , 
			"13437 5388 2784 3664 1342 241 18 0" , 
			"9043 4047 2352 2049 501 86 7 1" , 
			"3764 577 517 1202 1049 360 55 4" , 
			"96381 15 45782 23090 19187 6783 1369 155" , 
			"2433 453 601 666 555 147 10 1" , 
			"7293 2237 1984 1860 846 267 88 11" , 
			"26742 11357 4849 5205 3031 1436 655 209" , 
			"46171 7482 18183 11769 6683 1797 237 20" , 
			"7978 1201 3268 2076 1026 342 62 3" , 
			"9898 884 3071 3007 1806 884 213 33" , 
			"2995 779 745 932 446 83 10 0" , 
			"14057 1668 4105 5028 2557 595 94 10" , 
			"22393 1470 2044 4412 5870 5348 2604 645" , 
			"12189 3210 3183 3470 1730 518 72 6" , 
			"1405 269 475 551 90 17 2 1" , 
			"239 0 8 111 100 18 2 0" , 
			"23625 2702 3282 7167 6842 2976 592 64" , 
			"41908 8792 8968 9423 9215 4423 965 122" , 
			"9972 3174 3171 2615 850 140 20 2" , 
			"396 82 43 135 113 20 2 1" , 
			"10534 6969 1113 1412 792 212 34 2" , 
			"16182 1701 3761 5372 3843 1220 265 20" , 
			"74611 11583 15271 18633 16707 9068 3015 334" , 
			"9885 2573 2479 2823 1493 421 90 6" , 
			"6290 1121 710 2555 1637 240 26 1" , 
			"5219 2275 957 1252 561 155 19 0" , 
			"14808 2864 5332 4283 1916 362 48 3" , 
			"2654 410 652 921 506 137 28 0" , 
			"28897 8907 4101 7121 5971 2286 489 22" , 
			"9524 5373 1895 1750 434 62 10 0" , 
			"12234 1558 2112 3256 3696 1351 242 19" , 
			"39720 4652 10566 11329 8287 3784 1005 97" , 
			"28646 2244 10825 8816 5050 1483 213 15" , 
			"701 29 154 315 159 39 5 0" , 
			"29253 2561 9224 9152 6055 1737 416 108" , 
			"12872 5227 3207 2997 1110 271 54 6" , 
			"8071 1615 2352 2310 1372 400 22 0" , 
			"1569 455 283 444 311 72 4 0" , 
			"548 33 294 198 21 2 0 0" , 
			"16127 3099 4869 4839 2405 760 135 20" , 
			"6066 770 3043 1089 632 411 118 3" , 
			"23112 3633 7652 6547 3886 1132 238 24" , 
			"12345 0 4 832 4519 4812 1813 365" , 
			"8627 3447 2484 2139 456 87 14 0" , 
			"11882 3372 3064 3394 1624 380 45 3" , 
			"5968 2820 627 974 996 464 80 7" , 
			"1023 443 200 306 65 9 0 0" , 
			"3169 1103 931 860 239 31 4 1" , 
			"357 23 161 123 40 8 2 0" , 
			"21200 11007 5377 3624 935 213 39 5" , 
			"29503 0 4358 4629 9999 7523 2421 573" , 
			"1367 587 349 291 103 26 9 2" , 
			"10582 2950 2642 3428 1260 263 35 4" , 
			"3120 37 1216 1379 421 56 11 0" , 
			"1263 436 230 427 142 26 2 0" , 
			"14941 2732 3459 4647 3011 889 189 14" , 
			"15946 1498 2496 5265 4764 1636 261 26" , 
			"31480 7248 9886 6845 4166 2495 742 98" , 
			"7343 2406 1640 2076 976 202 42 1" , 
			"29621 1856 5746 9895 8706 2968 421 29" , 
			"20943 2540 2449 3147 3575 4430 3412 1390" , 
			"16734 2185 5311 4789 3176 1039 218 16" , 
			"4202 1806 1156 1000 202 32 5 1" , 
			"968 5 104 552 253 51 3 0" , 
			"634 228 225 97 62 20 2 0" , 
			"11978 4625 3132 2660 1153 348 55 5" , 
			"1798 491 229 509 359 161 46 3" , 
			"5257 667 1163 848 870 1054 516 139" , 
			"4856 2530 1007 979 309 28 3 0" , 
			"6221 785 2040 2280 948 159 7 2" , 
			"769 558 109 72 26 4 0 0" , 
			"4449 2665 809 772 163 37 3 0" , 
			"7135 2775 1771 1849 560 160 19 1" , 
			"21738 7372 5829 4271 2766 1246 245 9" , 
			"705 353 126 183 37 6 0 0" , 
			"4001 1772 1122 907 164 32 4 0" , 
			"8792 4450 1859 1749 637 88 9 0" , 
			"9892 3090 3080 1764 1329 547 71 11" , 
			"6912 2771 1170 1903 863 183 21 1" , 
			"6900 3799 1659 1008 326 94 11 3" , 
			"11838 2017 3972 3252 1957 532 97 11" , 
			"1039 188 230 409 176 34 2 0" , 
			"20919 4183 3417 4742 4812 2935 820 10" , 
			"10302 4049 2715 2383 858 232 56 9" , 
			"64 5 2 22 25 9 1 0" , 
			"497 112 148 172 58 7 0 0" , 
			"470 84 43 218 108 15 2 0" , 
			"568 282 96 129 50 9 2 0" , 
			"10170 4102 1927 1513 802 1113 619 94" , 
			"8167 3536 1809 1835 775 175 32 5" , 
			"1635 275 435 478 300 115 32 0" , 
			"3882 632 1029 1358 645 173 38 7" , 
			"2487 562 1110 591 200 22 1 1" , 
			"17831 2411 7330 5314 2168 521 80 7" , 
			"3398 1034 781 1046 458 74 5 0" , 
			"19237 10094 3449 4041 1337 268 42 6" , 
			"11615 1825 2666 3731 2643 659 84 7" , 
			"940 275 148 307 184 23 3 0" , 
			"268 6 51 107 82 20 1 1" , 
			"5206 563 2229 1636 632 133 13 0" , 
			"2414 617 1211 395 154 32 3 2" , 
			"2611 1315 443 627 206 19 1 0" , 
			"4422 2650 878 697 171 24 2 0" , 
			"3836 1622 856 990 337 30 1 0" , 
			"17983 2094 4889 6220 3533 993 220 34" , 
			"3292 41 310 1404 1155 328 47 7" , 
			"5480 612 2308 1530 717 241 59 13" , 
			"13431 5731 4109 2053 1031 431 70 6" , 
			"6064 1132 1592 2035 1010 256 34 5" , 
			"5508 2837 875 876 705 181 31 3" , 
			"774 216 150 306 82 18 2 0" , 
			"622 367 165 72 16 2 0 0" , 
			"950 388 234 249 65 12 2 0" , 
			"2089 901 459 530 169 27 3 0" , 
			"1672 371 783 405 94 15 4 0" , 
			"3354 427 867 1262 597 164 36 1" , 
			"1308 611 327 271 70 23 3 3" , 
			"620 31 156 162 187 80 4 0" , 
			"6225 2080 1939 1529 564 94 18 1" , 
			"775 532 143 77 20 3 0 0" , 
			"9700 1938 4428 2168 911 217 33 5" , 
			"2982 1008 735 802 313 98 26 0" , 
			"14959 3913 4368 4329 1823 442 75 9" , 
			"8350 2217 1984 2374 1291 403 75 6" , 
			"3164 1137 1039 716 225 41 6 0" , 
			"5375 1763 1901 1247 372 74 14 4" , 
			"4475 989 1568 1118 548 192 50 10" , 
			"812 259 268 199 73 10 3 0" , 
			"1269 362 346 429 110 22 0 0" , 
			"4111 1957 607 1078 393 70 6 0" , 
			"3152 1257 833 666 318 67 8 3" , 
			"77 45 1 21 9 1 0 0" , 
			"992 586 162 170 65 8 1 0" , 
			"2367 946 659 470 234 48 10 0" , 
			"6745 2003 1501 2085 956 180 19 1" , 
			"916 625 116 144 26 4 1 0" , 
			"338 134 68 104 28 3 1 0" , 
			"3785 2082 867 569 216 39 11 1" , 
			"78 53 8 14 3 0 0 0" , 
			"9326 358 1304 2843 2952 1509 326 34" , 
			"1432 228 393 484 302 22 3 0" , 
			"1192 186 399 431 128 32 10 6" , 
			"2601 1183 402 711 271 30 4 0" , 
			"1838 278 754 595 169 38 3 1" , 
			"1962 248 741 579 304 73 16 1" , 
			"1768 263 185 672 456 146 38 8" , 
			"307 163 25 86 30 3 0 0" , 
			"68 27 9 23 8 1 0 0" , 
			"2902 789 699 532 348 371 156 7" , 
			"1741 38 133 701 582 230 48 9" , 
			"1093 225 296 352 194 26 0 0" , 
			"986 198 248 391 136 13 0 0" , 
			"7069 2616 1856 1767 658 137 32 3" , 
			"1003 163 338 382 95 14 9 2" , 
			"9690 3726 2570 2415 799 161 19 0" , 
			"3094 781 995 926 337 49 6 0" , 
			"831 420 218 162 30 1 0 0" , 
			"355 42 47 190 73 3 0 0" , 
			"2823 1440 426 358 400 165 30 4" , 
			"1172 629 266 200 44 28 3 2" , 
			"383 49 136 157 36 5 0 0" , 
			"1498 668 171 402 216 38 3 0" , 
			"1576 275 304 500 373 108 14 2" , 
			"9995 5753 2383 1443 343 69 4 0" , 
			"915 344 97 318 146 10 0 0" , 
			"155 79 12 54 7 3 0 0" , 
			"2542 1070 679 642 120 24 7 0" , 
			"584 16 362 166 37 3 0 0" , 
			"1317 503 327 386 88 13 0 0" , 
			"5164 1360 1755 1147 658 222 22 0" , 
			"7012 3275 2004 1237 376 107 13 0" , 
			"353 130 92 108 21 2 0 0" , 
			"1769 376 667 511 186 24 5 0" , 
			"349 70 106 134 33 6 0 0" , 
			"981 0 45 240 367 206 84 39" , 
			"1459 775 220 274 154 36 0 0" , 
			"2525 1393 341 491 248 47 5 0" , 
			"222 25 83 99 15 0 0 0" , 
			"78 9 3 35 25 5 1 0" , 
			"643 181 214 189 48 11 0 0" , 
			"1833 201 766 609 199 44 14 0" , 
			"613 279 153 130 44 6 1 0" , 
			"4872 2136 1007 1224 405 93 6 1" , 
			"1257 238 408 422 168 19 2 0" , 
			"239 3 96 125 15 0 0 0" , 
			"1476 250 519 516 172 16 3 0" , 
			"2330 1168 406 383 300 62 11 0" , 
			"220 18 66 90 39 6 1 0" , 
			"7651 3944 2020 1246 338 91 9 3" , 
			"4675 1496 1304 1306 445 106 18 0" , 
			"747 267 242 175 58 4 1 0" , 
			"1688 437 615 481 128 24 3 0" , 
			"764 509 111 133 11 0 0 0" , 
			"73 15 51 6 1 0 0 0" , 
			"239 0 47 135 47 9 1 0" , 
			"1024 151 334 410 114 14 1 0" , 
			"641 37 250 244 94 14 2 0" , 
			"777 255 185 207 103 26 1 0" , 
			"679 415 49 140 65 9 1 0" , 
			"598 94 291 142 54 17 0 0" , 
			"1499 967 230 250 45 6 0 1" , 
			"1547 846 278 290 111 21 1 0" , 
			"199 76 44 65 10 2 2 0" , 
			"357 307 28 22 0 0 0 0" , 
			"2478 771 1179 364 127 34 3 0" , 
			"430 284 96 36 12 1 1 0" , 
			"750 185 265 244 52 3 1 0" , 
			"847 280 187 206 125 42 7 0" , 
			"17 0 2 6 7 2 0 0" , 
			"1913 613 412 456 282 117 33 0" , 
			"157 12 39 77 27 2 0 0" , 
			"931 147 177 346 208 50 3 0" , 
			"2358 866 500 758 215 18 1 0" , 
			"1184 508 439 187 40 9 1 0" , 
			"1168 573 213 255 105 20 2 0" , 
			"2929 904 1369 499 118 37 1 1" , 
			"5619 3098 593 1358 506 56 8 0" , 
			"453 52 171 171 55 4 0 0" , 
			"226 111 19 73 23 0 0 0" , 
			"52 2 26 20 2 2 0 0" , 
			"621 10 173 176 212 47 3 0" , 
			"3135 436 782 1208 558 109 34 8" , 
			"2419 743 395 827 389 61 4 0" , 
			"896 661 99 119 16 1 0 0" , 
			"562 39 358 151 13 1 0 0" , 
			"1623 177 506 526 290 112 10 2" , 
			"1177 651 237 219 55 9 5 1" , 
			"84 26 32 24 2 0 0 0" , 
			"1283 181 330 445 238 83 6 0" , 
			"415 63 173 118 47 13 1 0" , 
			"2409 517 883 634 244 101 27 3" , 
			"98 17 10 57 11 3 0 0" , 
			"257 148 60 33 15 1 0 0" , 
			"371 110 96 96 50 14 4 1" , 
			"938 593 116 181 37 10 1 0" , 
			"358 119 147 66 23 2 1 0" , 
			"784 274 260 192 42 16 0 0" , 
			"824 343 197 235 40 8 1 0" , 
			"1435 1390 24 16 2 3 0 0" , 
			"544 194 116 192 41 1 0 0" , 
			"1494 676 299 388 115 13 3 0" , 
			"126 40 29 53 4 0 0 0" , 
			"25 8 15 1 1 0 0 0" , 
			"944 289 244 292 74 39 4 2" , 
			"7735 5179 1518 808 181 37 11 1" , 
			"756 0 679 35 29 10 3 0" , 
			"1474 864 171 304 121 11 3 0" , 
			"301 0 1 222 69 9 0 0" , 
			"759 369 191 160 32 4 3 0" , 
			"1489 1010 253 184 38 4 0 0" , 
			"392 216 80 77 14 5 0 0" , 
			"886 324 209 299 50 4 0 0" , 
			"729 104 184 330 97 13 1 0" , 
			"398 18 153 171 50 5 1 0" , 
			"968 113 214 409 201 30 1 0" , 
			"55 46 6 3 0 0 0 0" , 
			"168 33 32 74 27 2 0 0" , 
			"212 43 31 97 38 3 0 0" , 
			"2280 1986 96 137 55 4 2 0" , 
			"5 3 0 2 0 0 0 0" , 
			"1009 317 319 281 79 12 1 0" , 
			"305 24 173 90 15 2 1 0" , 
			"156 37 60 45 8 4 2 0" , 
			"345 117 90 97 35 6 0 0" , 
			"1957 1196 373 277 86 23 2 0" , 
			"108 1 70 19 10 8 0 0" , 
			"416 154 59 179 18 3 3 0" , 
			"728 588 64 61 11 2 2 0" , 
			"175 17 56 25 53 21 3 0" , 
			"417 290 46 57 20 4 0 0" , 
			"71 28 17 22 3 1 0 0" , 
			"143 0 6 132 4 0 1 0" , 
			"454 237 70 104 34 9 0 0" , 
			"964 306 269 268 105 15 1 0" , 
			"1357 730 253 258 98 18 0 0" , 
			"594 288 133 140 31 2 0 0" , 
			"926 427 233 218 43 4 1 0" , 
			"354 0 165 121 41 19 6 2" , 
			"592 327 146 94 21 2 1 1" , 
			"370 178 51 99 39 3 0 0" , 
			"109 40 23 34 8 1 2 1" , 
			"250 43 66 92 43 6 0 0" , 
			"423 266 110 40 4 3 0 0" , 
			"1187 681 212 237 47 10 0 0" , 
			"189 1 62 55 27 30 14 0" , 
			"729 346 205 154 18 6 0 0" , 
			"1076 522 325 194 30 3 1 1" , 
			"410 316 32 52 9 1 0 0" , 
			"410 221 84 83 22 0 0 0" , 
			"2629 514 957 780 331 46 1 0" , 
			"152 98 15 35 2 2 0 0" , 
			"136 34 35 50 17 0 0 0" , 
			"705 478 104 99 22 1 1 0" , 
			"307 34 79 162 30 2 0 0" , 
			"889 410 137 258 80 4 0 0" , 
			"621 442 69 68 35 7 0 0" , 
			"138 9 33 65 26 5 0 0" , 
			"501 399 31 47 22 2 0 0" , 
			"66 21 20 23 2 0 0 0" , 
			"113 52 6 49 5 1 0 0" , 
			"84 0 50 17 14 2 1 0" , 
			"153 40 47 48 11 5 2 0" , 
			"467 165 126 102 56 17 1 0" , 
			"249 38 70 103 31 6 1 0" , 
			"211 0 30 110 63 7 1 0" , 
			"236 121 61 35 15 4 0 0" , 
			"1136 128 243 446 267 48 4 0" , 
			"822 649 29 96 45 3 0 0" , 
			"570 370 112 61 25 2 0 0" , 
			"562 332 113 86 28 3 0 0" , 
			"32 29 1 1 1 0 0 0" , 
			"708 27 358 212 98 13 0 0" , 
			"9 3 3 3 0 0 0 0" , 
			"84 10 27 40 2 2 3 0" , 
			"109 87 13 7 1 1 0 0" , 
			"92 0 15 68 7 2 0 0" , 
			"62 29 16 12 4 1 0 0" , 
			"210 174 8 24 3 1 0 0" , 
			"74 37 22 13 2 0 0 0" , 
			"82 58 14 10 0 0 0 0" , 
			"8 0 1 5 1 1 0 0" , 
			"98 6 91 1 0 0 0 0" , 
			"13 0 11 2 0 0 0 0" , 
			"55 14 25 12 4 0 0 0" , 
			"288 225 25 27 10 1 0 0" , 
			"171 17 11 121 22 0 0 0" , 
			"15 10 3 1 1 0 0 0" , 
			"118 88 12 12 6 0 0 0" , 
			"388 226 68 79 12 3 0 0" , 
			"286 131 40 76 38 1 0 0" , 
			"31 1 1 16 13 0 0 0" , 
			"1421 1404 3 13 1 0 0 0" , 
			"134 3 52 48 26 5 0 0" , 
			"150 37 37 51 22 2 1 0" , 
			"8 7 1 0 0 0 0 0" , 
			"145 76 22 43 1 3 0 0" , 
			"75 60 6 6 3 0 0 0" , 
			"36 1 23 11 1 0 0 0" , 
			"161 121 15 18 7 0 0 0" , 
			"785 704 33 33 12 3 0 0" , 
			"11 9 1 1 0 0 0 0" , 
			"6 0 0 5 1 0 0 0" , 
			"83 0 4 61 8 9 1 0" , 
			"6 0 4 2 0 0 0 0" , 
			"39 10 19 10 0 0 0 0" , 
			"178 174 1 3 0 0 0 0" , 
			"6 0 3 3 0 0 0 0" , 
			"199 34 45 74 41 5 0 0" , 
			"62 20 17 24 1 0 0 0" , 
			"218 202 6 8 2 0 0 0" , 
			"35 23 1 5 6 0 0 0" , 
			"37 37 0 0 0 0 0 0" , 
			"23 12 11 0 0 0 0 0" , 
			"32 1 12 12 6 1 0 0" , 
			"11 7 1 3 0 0 0 0" , 
			"276 252 7 16 1 0 0 0" , 
			"1 1 0 0 0 0 0 0" , 
			"33 18 4 4 7 0 0 0" , 
			"118 106 2 9 1 0 0 0" , 
			"45 9 25 6 3 2 0 0" , 
			"9 0 2 7 0 0 0 0" , 
			"9 6 1 1 1 0 0 0" , 
			"3 1 2 0 0 0 0 0" , 
			"11 9 0 2 0 0 0 0" , 
			"10 9 1 0 0 0 0 0" , 
			"7 3 3 0 0 1 0 0" , 
			"86 28 2 54 2 0 0 0" , 
			"515 510 2 3 0 0 0 0" , 
			"72 71 0 1 0 0 0 0" , 
			"2 2 0 0 0 0 0 0" , 
			"1 0 0 1 0 0 0 0" , 
			"1 0 0 1 0 0 0 0" , 
			"9 1 6 2 0 0 0 0" , 
			"3 0 2 0 1 0 0 0" , 
			"3 3 0 0 0 0 0 0" , 
			"10 1 8 0 1 0 0 0" , 
			"65 62 2 1 0 0 0 0" , 
			"3 1 1 1 0 0 0 0" , 
			"8 1 7 0 0 0 0 0" , 
			"2 1 0 1 0 0 0 0" , 
			"11 11 0 0 0 0 0 0" , 
			"3 3 0 0 0 0 0 0" , 
			"14 14 0 0 0 0 0 0" , 
			"6 6 0 0 0 0 0 0" , 
			"3 0 2 1 0 0 0 0" , 
			"1 0 1 0 0 0 0 0" , 
			"71 66 4 0 1 0 0 0" , 
			"4 2 2 0 0 0 0 0" , 
			"4 0 3 0 1 0 0 0" , 
			"1 0 1 0 0 0 0 0" , 
			"1 0 1 0 0 0 0 0" , 
			"1 1 0 0 0 0 0 0" , 
			"1 1 0 0 0 0 0 0" , 
			"14 14 0 0 0 0 0 0" , 
			"1 1 0 0 0 0 0 0" , 
			"1 0 1 0 0 0 0 0" , 
			"6 5 0 1 0 0 0 0" , 
			"2 0 2 0 0 0 0 0" , 
			"1 0 0 1 0 0 0 0" , 
			"1 0 0 1 0 0 0 0" , 
			"1 0 1 0 0 0 0 0" , 
			"8 0 8 0 0 0 0 0" , 
			"8 0 8 0 0 0 0 0" , 
			"1 0 1 0 0 0 0 0" , 
			"1 0 1 0 0 0 0 0"};
	
	public static String nameChar= "丁关根子申万历钢举牌哥乌多尔夫科特日图马拉乔布斯石买提江亚努维奇当伊诺欣今达伍跃时伽利略侯赛因保罗俾麦傅全有傈元芳光绪帝克林顿内韦列宁刘伯承少邦加尼古丽千百惠华庚卡梅伦滨叶淑仪钦吴晓国周恩来哈姆珀米德里白唐太宗培基奎洛塔曼塞西增奈蒂考奥巴朗本海默雷莱女娲拜姜春云威逊嫦娥贡路孔孙中山悟空孟安烈鲁宋健庆龄密富先尉行小萝卜头尚森松娜居人屈原岳飞峥崇祯扎沃萨沙甫金什兰迪希勒帕晗平狄庄建章弗格张年彭怀珮徐悲鸿若谷赫包恰武卢戴高乐才旦打工者托扫帚姐拇指妹蒙拿破仑撒切收碗断臂大腾坎普京曹操曾凯红朱熹镕李成念四钊家祥岚清珍瑞环适逵铁映鹏杜杨振洁箎篪杰赞干照肯柏津采岛比约毕索毛泽东汉景民法波涅温宝爱坦资牛群莎献王兆秋玛雅妮瑟瓦连田求皮祖福秦始皇程思远穆罕纳琴吉绍荣桓耶和酥肉孜胡锦涛舒艾迈苏轼茅盾茨士莫菜花甜妈菲齐董蒋介薄一街净角野诸葛亮敦贝谢盖芬费贺贾冲辜胜阻式赖喇嘛迟浩送水邓邱邹郭沫酋崎正钟离钱其琛学门捷阿bensair兹博埃方木末都陆游陈标冯陶各雨果锋额道越迅黄蕾黑俊良北蓓碧彪宾彬斌冰兵彩霞灿河常青畅超朝阳瑛晨诚宏城橙楚怡川传伟莲明艳聪翠玲妲黛丹得礼琳龙生文荻笛地娣冬朵发凡兴妃非霏斐丰风枫峰烽凤佛芙刚岗歌官辉桂香栋强永忠柱军燕洋英涵寒好昊皓禾合荷恒娟卫星宇虹洪磊厚义湖虎信桦欢珲晖卉会慧火姬季继佳珈静嘉坚敏勇剑娇姣婕堂秀进菁晶婧敬靖菊莉爵均君骏珺恺康珂柯可儿坤昆锟蓝澜黎藜理力立雯美萍珊媛俐娅恋靓霖麟灵芝志苓铃凌璐菱岭柳隆露绿茂玫眉琪球媚萌梦苗旻珉鸣墨沐慕南楠能倪霓凝农欧攀盼佩朋蓬丕善蒲璞琦祺麒启芊茜倩玉巧芹勤卿情晴芸琼然燃仁蓉柔如茹蕊芮锐睿润三色姗真贵声圣男晟盛诗之世仕姝曙树帅双爽烁司丝嵩颂素韬天亭婷通佟彤桐童崴湾晚婉旺葳微薇巍为玮未纹闻夕溪熙曦喜细侠夏显湘翔向萧潇笑心芯辛昕新歆馨鑫雄修旭轩宣萱暄煊蛟雪熏延妍岩炎彦焱扬遥瑶烨帆衣依贻亿艺易轶逸毅翼茵荫范银寅樱迎盈莹营颖咏优友航羽语洲煜豫园圆源月悦长征哲贞政知治智舟竹壮卓辰梨妙恬贤薛霸佰定帮豹丙泊勃财策茶禅昌在昶潮臣尘郡澄驰闯纯淳慈从容丛存钧业岱恭尊登魁第典顶鼎阁升梁豆逗渡铎顺仙纲港戈巩广实归公亨含豪衡弘渐琥户骅淮槐焕尧寂骥甲乙俭简见键将疆节妤颉瑾劲晋娴炯九久璨筠浚开旋恪琨浪垒璃影荔邻临陵翎流禄芒汀盟猛弥宓谧淼名珠铭牧那耐暖沛棚品泰坡期岐歧淇骐棋旗起绮谦前乾黔权泉冉饶任溶榕儒韶邵师耿守寿舜硕焘桃体庭停挺同铜拓委炜蔚魏锡习相想霄筱孝协缘代杏熊许玄璇炫勋烟言研颜琰晏雁央旸耀冶邺沂宜以忆屹亦异奕益谊意懿音隐印婴应幼佑于俞渝瑜禹玺郁育昱钰遇喻裕苑玥昀运模占战昭召喆震镇郑芷致仲众重梓紫左佐作璋祚敖秉炼铮傲菡鹰次斗度侨嫒昂翱何备壁璧边变炳粲藏岑婵蝉澈沉宸池禧椿琮单荡棣殿蝶动鹃端二姑璠舫放分粉杉楼符改罡皋娃冠圭棠翰濠号颢鹤珩鹄化画换荟蕙霍极纪冀骞舰涧交皎解巾谨荆璟驹巨绢珏峻侃轲宽鲲腊郎琅桑冷忱栎联练粱辽淋伶珑羚绫零令鹿潞鹭麓骆满莽枚棉茗茉漠牡乃准柠鸥苹屏朴浦契迁慎潜浅亲芩霜渠曲雀韧茸绒汝蕤闪裳劭舍社深镜彝书述朔菘锁谭檀滔骄添廷瞳团菀琬唯纬选翁无瑕五惜晰羲险宪肖骁宵啸幸岫絮煦瑄飘严砚焰漾曜也晔杭夷颐羿翊熠吟胤姿莺璎荧瀛涌用悠又余渔榆屿钗钏桥兔箫渊琢彧峪毓垣牙枝钺赟鸽上仔耘允韵再展命桢臻宙竺祝专转灼自丞汇或鉴坪翘苇栩崖衍泳直州班固觉斓配示霆校必弼聃淦封函佶聚隽楷量鸾勉闵去融粟叙漪训赢蕴拯奚夜遵炬录煌鲍防革泓际鲸井徽仟嵘十望暴舞淡聿至舸功瀚经竞懋擎庶泗皖予汶庵沅争梧赋馥敢竟阔澎频峤沁续涯滢域卞灏穗誉别牟宴效阅镝繁梵抗商尹步近犁溢蔡凉弟岸樟葆堡报焯呈初创萃党支藩铧侗笃墩鄂锷坊昉逢芽扶孚付复嘎宫共观圻瑰韩沆回机济霁璘楣励检彰教榜精铠锴葵厉绵笠领六盘陇垄轮履律蔓漫朦蓁慰闽锜谋潘植裴祈牵俏丘荃苒忍愚雍序伸沈施抒叔漱澍偲速潭赐助注弯宛廉惟渭土嫣稳忌吾午鲜襄详笙谕醒銮气绣循堤演炀仰铣象侬邑怿翌殷郢拥邕庸村於愉袁院瑗粤鋆韫湛招肇浙评质茁仓苍弦库奔不参察场乘赤炽涤甸房汾伏辅冈耕更贯随后奂涣璜积激寄骧猷燎佼界泾兢矩据竣镛难寇焜镭骊涓留落吕勐冕鼐派畔圃谱颀箐表尤恕铄塘针畏务僖现羡响燮休琇绚询恂珣讯筑俨钥旖弋议译弈引颍幽禺瑀则记阵筝挚主著铸飙垂淙点而奋甘铨寰集几荐旌揆劳率旎娉省淞遂探养涂汪尾享濂寻洵祎疑樵由愈愿追曰醉查览嗣屋巡柴夔坛藤薪绯奉唱雕捍箭戎途项缨员晁错巢车骋戟泥胄扁蔼霭湄桉鳌岙澳八坝草材半倍贲彼镔铂搏沧册绰钿彻谌称持充宠畴席玖甄词祠辞甬葱狮位灯佃洞段夺奖蕃樊仿沣兮浮阜赣杲镐告赓台龚珙顾管规炅粹汗阶掌帼过还毫郝横晃闳绶瑚沪祜幻浣挥恢绘荪玑楫己计匡绩谅楹泠让墉稼戬践绛郊皆结玠伙卯圳足尽靳纶藻憬警境酒就桔赏亢垦铿扣快老棱鹂鲤身枢夙郦俪栗粒涟琏链谟粮廖鎏炉论珞旅麻嫚矛檬锰觅蜜岷薰滋陌睦钮泮庞器站漂嫔聘铺溥七妻祁企杞洽虔诒墙蔷嫱峭琚汐取柄染娆壬悌熔统闰斋飒堰厦涉莘燊绳样湜史事是首受虞蜀术束数墅说邈潼危乡肃溯岁所滩汤淘套厅听艇外完圩围稷嵬娓味问昔稀遐咪咸衔线枭哓逍娘赵勰忻援造性宿须胥虚漩烜笋蘅滟翡姚要霈榛绎已镒垠锵宥鱼与浴预欲御督塬载瓒臧早产宅瞻匀诏箴朕诤执祉峙稚洙翥状倬族钻最尖卷纤殊抱识琤耳概旷目纵仇储褚县崔护蠡峦渺滕移铉膺赜焦裔但电府及浓绥洼倚邸刁覃渤玎缓唤忧溟濮趣服袭讷勍栓宰整沄窦绾庠蔺技汕浔拙拔住穹弛郜椒囡翩戚扩客缪墀释邢羊证刃艮嫩勖鸯籍迦磬鳞巽洒玢敞翻蜂构获接芦栾磐砺拴谈峡沿呼樾徵烛聂况弓蒿勾苟窑浑寺寨圈阮隗晞系诩攸秒神娱寓择综间笔惊堪秘巷泱灶衷脉汛庐候船刀跳榴骑热绅职扈措骝滑带凰辑驷总径鞠矿区纱调网雾犀旬荀眼秧右詹郗息嵇骢监敷翟医缙坞揭阙阚蒯邝裙浜屯丫芾感活妞累栖奴裘诠侍戊缇壹婆秩字阴啊埠处排娄闫逯迹种母漆亓瞿佘屠徒隋邰鸳邬巫庙冼店刑鄢阎昝迷訾厄瓜缅瑙纽芭歇夸摩玻梭迭剛順羅為橋瑠栄優語閉貞邊龍輔勝哉軍部愛渚樹長徹誠純園達児蔵徳東篤豊飛鳥亙実広規亀貴華恵絵紀記継呂潔謹進寛頼櫟斎輝則竜満穂夢磨斉謙慶譲顕榮潤紗渉愼昇市準隼鉄統馬賢嵐憲暁興勲薫艶陽義祐見壽伝丈眞備陳暠賀簡瞭倫寧喬権識員鎮計曽沼出島陣戸聡開矢沢澤具瀬賞幾姫歩蘇亜萬風形稔時涼座织咲絢欽宕諒設葉電拾陰種淸緑館黒荒倉麗巌麿騎氏親兄組磯織駿発峯鷹凛場農悪結緒鲇礁経遙瑤膳帯揺円連蓮聖尋尭堯層冨綱岡覚雲伴廣國積済間紘丸頓會鍵算軒侑剣節當錦濃幹食郷総室透蒜孫洸鶴狂綾吹穣梢巳師誓響門楢與奏範亘堅講拡了団養資暎護衛藝桜蕗顯楽荘兼樂齋車関啓紳監鞍掛口琉遠辺紡績鎌彌貢気魚圓案納務悳惇濑鋭蘭遊機諭後質嶋宮楓禮貫渓復暢蔭壱皐紅儀廊柿創劔鉱鑛怜碩頴穎脇須埜僚舗薗塚奧坂板濱売並焼嶌幡巻堀様芋製反醸運鐘匠衆弐潟摘峠瞬柊乗鍬手鮎条條塩役隅俣針調樫抜棟下戦跡紺渥訓髪専專測鯉輪浄酔鐡鐵脩鷲瀧苺吏鸟鴻庫鈴異踊籏砂験紙葭岬糸別滝畑兎檎弁図雏増収栢拝稗斑鳩阪奨颯協伎財導嶽將遼詩實魯埴寸頂龜橘勘齢禰蓑簑額詰漁湧観嬢領苅供銀爪鼻滉従該職視評絹鉾退稲裁麺紹鬼曠面産陸苔検窪渦龟鏡迫駒無刈鴇寶氷側町頭繪燦來歳惣驥楯舘極裏塾厳筋禎勢狩弾畠雋維煕業覇個誼蟹押硝亞綴折貝淵渋報等釣副鋼靜稜刻鄰鵬粛仮窓莊漸只聞階凜願揚諏訪筆俵湯細總兒濵冴動働并床傳稙夛薬槙止鉢嶺播鑑拳灘急囃懐熈汰袋現柚飯聰汲骨燿絋税蒼絡凪薙隊縄我梶物蕭廼詫幕辻錬夘廬椙閑昴祢沖紋暉偉槇祭甚爾待強毬摂踏崚録挙瀞諸軌萠局藍肥銈寮鶏類渕蘆盤曻詳巣滴詮杯隈霧鮮櫻燈労浬詣単逆邉酉畝処恆蕉憂入諦讃粂応胁離倭砦桁銅穴父勅使艦砲丑射湊鳳厨鋤杵椰億舎锅鍋賜蕨爲衞謨塗料甕揮盧舩閘曳舛串槌毎此楊詔帰到栞鞆醇銃籐嵯峨凱対論番乱負狭虫燁枠続歯炊紬榎蓼署淀碇仏髙箇給賣暦詞溝句澪廟槻撫練旨猿啄徴躬嵜袈裟菅緋捨礒婦往択偵鋸鯛隣泷詠鳴堇內傑圏碕訥掃鎭萩郞倖祇油縁巖窯苧環漣笹薮藪像霊毘陀猫賴應懸扇鈍轟雑廃載縦掾鉦張櫛冢氣魅築誌乳呉穀邨儔濟的叡短憐蔦醍繭菫迂稻鍛聲独鏑砥厩菟釘両漢掬邇苫樋諌纮曉審錠鴎槍鷗銕僊碓對鰐楳瓶鮫筏桝饭篠赳騨晧絃版劇鋳釜柑銑栁還茲關歓鐐遷銭萢势讓粕糟肝绀昼綺蛍査杢術孤庁壯似賛際雫証臨稟汎晄撰樗學僧磁藁綿飾莞騰銘綏削薩妥弌嚴臺腰諄沟菰頌韻黙鯨謝札涙馆習籠團菴桧闘附補淨説勁底寬瀨錯晩談決蒔詢獅制縫壷壺隠椛樺灰檜箱矶箕鼓書茄鷺笈腸鋪話標筧諫刺塁降纐纈芥堺葦攝烏錫柵軽壌筒置瑳匂韮臼尺鎧糠込袴萤看蛯闇觀栃饒菓蛎蠣執镰丼簗椋柾祿滿溜聴項櫨飼栂寝終皿洗散暮樽漬覺璽難喩濯籾架片雛勧線塀彥埼畦齊疋駄伺勳淺鎗靑棲諾乕穐蜷翫塙熱限宍權竿魂釈救惺枡鳶閥澁許倶療粧糖鱗顔猶疎確蒸消縣笘轍盃閏榊篁返榑割互彗椎筈從判蝦莚竪硯螢絲杣炭枩闲菖濤沓閤墾免亥穏嬉姉鵜枷網鎖桶哀鑓問籔扉株採梠適朽袖盐幌揖飴楡輿鍔桟柘雉笥払箸诹访厘核";
}
