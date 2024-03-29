package webspider;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Queue;

public class Crawler
{
	private void initCrawlerWithSeeds(String[] seeds)
	{
		for (int i = 0; i < seeds.length; i++)
			LinkQueue.addUnvisitedUrl(seeds[i]);
	}

	public void crawling(String[] seeds)  // 定义过滤器，提取以http://www.lietu.com开头的链接
	{
		  LinkFilter filter = new LinkFilter() 
		  {
		   public boolean accept(String url)
		   {
		    if (url.startsWith("http://www.baidu.com"))
		     return true;
		    else
		     return false; 
		   }
		  };
		  initCrawlerWithSeeds(seeds); // 初始化 URL 队列
		  // 循环条件：待抓取的链接不空且抓取的网页不多于1000
		  while(!LinkQueue.unVisitedUrlsEmpty()&&LinkQueue.getVisitedUrlNum()<=1000)
		  {
			  // 队头URL出队列
			  String visitUrl = (String) LinkQueue.unVisitedUrlDeQueue();
			  if (visitUrl == null)
				  continue;
			  DownLoadFile downLoader = new DownLoadFile();
			  // 下载网页
			  downLoader.downloadFile(visitUrl);
			  // 该 url 放入到已访问的 URL 中
			  LinkQueue.addVisitedUrl(visitUrl);
			  // 提取出下载网页中的 URL
			  Set<String> links = HtmlParserTool.extracLinks(visitUrl, filter);
			  // 新的未访问的 URL 入队
			  for (String link : links) 
			  {
				  LinkQueue.addUnvisitedUrl(link);
			  }
		  }
	}// main 方法入口
	public static void main(String[] args) 
	{
		Crawler crawler = new Crawler();
		crawler.crawling(new String[] { "http://www.baidu.com" });
	}

}
