package edu.vinaenter.daos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.vinaenter.models.News;

@Repository	//connect to DB
public class NewsDao {
	public List<News> getList(){
		List<News> datas = new ArrayList<News>();
		datas.add(new News("1", "News1", "MR JohnA", new Date(), "detail 1", 1));
		datas.add(new News("2", "News2", "MR JohnB", new Date(), "detail 2", 0));
		datas.add(new News("3", "News3", "MR JohnC", new Date(), "detail 3", 0));
		datas.add(new News("4", "News4", "MR JohnD", new Date(), "detail 4", 1));
		datas.add(new News("5", "News5", "MR JohnE", new Date(), "detail 5", 0));
		datas.add(new News("6", "News6", "MR JohnF", new Date(), "detail 6", 1));
		return datas;
	}
}
