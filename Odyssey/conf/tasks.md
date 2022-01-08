# Question 


1. 修正数字串合并
2. 修正词性序列标注问题
3. bigram模块替换
4. 资源文件放入jar包内部

5. bigram 存入单独的hashmap中，分词后校验AnalyzerItem,Graph,ToAnalysis,<前词，后词，频次>

Graph.merger()->term.setPathScore(fromTerm);->MathUtil.compuScore(from, this);->NgramLibrary.getTwoWordFreq(from, to);