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

	public void crawling(String[] seeds)  // �������������ȡ��http://www.lietu.com��ͷ������
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
		  initCrawlerWithSeeds(seeds); // ��ʼ�� URL ����
		  // ѭ����������ץȡ�����Ӳ�����ץȡ����ҳ������1000
		  while(!LinkQueue.unVisitedUrlsEmpty()&&LinkQueue.getVisitedUrlNum()<=1000)
		  {
			  // ��ͷURL������
			  String visitUrl = (String) LinkQueue.unVisitedUrlDeQueue();
			  if (visitUrl == null)
				  continue;
			  DownLoadFile downLoader = new DownLoadFile();
			  // ������ҳ
			  downLoader.downloadFile(visitUrl);
			  // �� url ���뵽�ѷ��ʵ� URL ��
			  LinkQueue.addVisitedUrl(visitUrl);
			  // ��ȡ��������ҳ�е� URL
			  Set<String> links = HtmlParserTool.extracLinks(visitUrl, filter);
			  // �µ�δ���ʵ� URL ���
			  for (String link : links) 
			  {
				  LinkQueue.addUnvisitedUrl(link);
			  }
		  }
	}// main �������
	public static void main(String[] args) 
	{
		Crawler crawler = new Crawler();
		crawler.crawling(new String[] { "http://www.baidu.com" });
	}

}
