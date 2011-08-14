package com.giago.appengine.commons.sitemap.source;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class StaticSitemapSource implements SitemapSource {

	private static final List<Integer> MONTHS = Arrays.asList(Integer
			.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Integer
			.valueOf(4), Integer.valueOf(5), Integer.valueOf(6), Integer
			.valueOf(7), Integer.valueOf(8), Integer.valueOf(8), Integer
			.valueOf(9), Integer.valueOf(10), Integer.valueOf(11), Integer
			.valueOf(12));

	private static final List<Integer> days = new ArrayList<Integer>(); 
	static {
		for(int i = 1; i<= 31; i++) {
			days.add(i);
			i++;
		}
	}

	private List<Integer> years = new ArrayList<Integer>();
	private Iterator<String> relativeUrls;
	private Iterator<Date> modifiedDates;
	
	private Date currentModifiedDate;
	private String currentRelativeUrl;
	private String kind;


	public StaticSitemapSource(String kind) {
	    this.kind = kind;
	}
	
	public StaticSitemapSource(String kind, List<Integer> years) {
		this.years = years;
		this.kind = kind;
	}
	
	public StaticSitemapSource(List<String> relativeUrls, List<Date> modifiedDates, String kind) {
		this.relativeUrls = relativeUrls.iterator();
		this.modifiedDates = modifiedDates.iterator();
		this.kind = kind;
	}

	@Override
	public List<Integer> years() {
		return years;
	}

	@Override
	public List<Integer> days(int year, int month) {
		return days;
	}

	@Override
	public List<Integer> months(int year) {
		return MONTHS;
	}

	@Override
	public Date getCreatedDate() {
		return currentModifiedDate;
	}

	@Override
	public String getRelativeUrl() {
		return currentRelativeUrl;
	}

	@Override
	public boolean next() {
		if(relativeUrls.hasNext()) {
			currentRelativeUrl = relativeUrls.next();
			currentModifiedDate = modifiedDates.next();
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void prepare(int year, int month, int day) {
		// TODO Auto-generated method stub
	}

    @Override
    public String getKind() {
        return kind;
    }

    @Override
    public boolean isCreatedDateDefined() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public void prepare() {
        // TODO Auto-generated method stub
        
    }

}