/**
 * 
 */
package com.bulky.controller.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bulky.support.AppUtil;


/**
 * @author kaala
 *
 */
@Controller
public class UtilController {
	
	@RequestMapping(value = "/api/calendar/periodi" ,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String listPeriodi(@RequestBody String payload){
		JSONObject pl = new JSONObject(payload);		
		Integer anno = AppUtil.getIntegerValueOf(pl, "anno");

		JSONObject selected = new JSONObject();

		GregorianCalendar gc = new GregorianCalendar();
		gc.setTimeInMillis(System.currentTimeMillis());
		if (anno == null ){
			anno = gc.get(Calendar.YEAR);
		}		
		int index = 1;

		int anni[] = {anno,(anno-1)};
		JSONArray periodi = new JSONArray();
		for( int annox:anni){
			gc.set(Calendar.YEAR, annox);
			GregorianCalendar gcNow = new GregorianCalendar();
			String ID="ID";
			String DESCRIZIONE="DESCRIZIONE";
			String DATA_DA="DATA_DA";
			String DATA_A="DATA_A";

			gcNow.setTimeInMillis(System.currentTimeMillis());
			
			JSONObject p1 = new JSONObject();
			p1.put(ID, index++);
			p1.put(DESCRIZIONE, "ANNO "+annox);
			gc.set(Calendar.DATE, 1);
			gc.set(Calendar.MONTH,Calendar.JANUARY);
			p1.put(DATA_DA, AppUtil.DATE_FORMAT.format(gc.getTime()));
			gc.set(Calendar.DATE, 31);
			gc.set(Calendar.MONTH,Calendar.DECEMBER);
			p1.put(DATA_A, AppUtil.DATE_FORMAT.format(gc.getTime()));		
			periodi.put(p1);
			// primo trimestre
			Date[] primoTrimestre = trimestre(annox,Calendar.JANUARY);
			p1 = new JSONObject();
			p1.put(ID, index++);
			p1.put(DESCRIZIONE, "I Trimestre "+annox);		
			p1.put(DATA_DA, AppUtil.DATE_FORMAT.format(primoTrimestre[0]));		
			p1.put(DATA_A, AppUtil.DATE_FORMAT.format(primoTrimestre[1]));		
			periodi.put(p1);
			// secondo trimestre
			Date[] secTrimestre = trimestre(annox,Calendar.APRIL);
			p1 = new JSONObject();
			p1.put(ID, index++);
			p1.put(DESCRIZIONE, "II Trimestre "+annox);		
			p1.put(DATA_DA, AppUtil.DATE_FORMAT.format(secTrimestre[0]));		
			p1.put(DATA_A, AppUtil.DATE_FORMAT.format(secTrimestre[1]));		
			periodi.put(p1);
			// terzo trimestre
			Date[] terzoTrimestre = trimestre(annox,Calendar.JULY);
			p1 = new JSONObject();
			p1.put(ID, index++);
			p1.put(DESCRIZIONE, "III Trimestre "+annox);		
			p1.put(DATA_DA, AppUtil.DATE_FORMAT.format(terzoTrimestre[0]));		
			p1.put(DATA_A, AppUtil.DATE_FORMAT.format(terzoTrimestre[1]));		
			periodi.put(p1);
			// quarto trimestre
			Date[] quartoTrimestre = trimestre(annox,Calendar.OCTOBER);
			p1 = new JSONObject();
			p1.put(ID, index++);
			p1.put(DESCRIZIONE, "IV Trimestre "+annox);		
			p1.put(DATA_DA, AppUtil.DATE_FORMAT.format(quartoTrimestre[0]));		
			p1.put(DATA_A, AppUtil.DATE_FORMAT.format(quartoTrimestre[1]));		
			periodi.put(p1);

			// primo quadrimestre
			//		Date[] primoQmestre = quadrimestre(anno,Calendar.JANUARY);
			//		p1 = new JSONObject();
			//		p1.put(ID, 6);
			//		p1.put(DESCRIZIONE, "I Quadrimestre "+anno);		
			//		p1.put(DATA_DA, Costanti.dateFormat.format(primoQmestre[0]));		
			//		p1.put(DATA_A, Costanti.dateFormat.format(primoQmestre[1]));		
			//		periodi.put(p1);
			//		// secondo quadrimestre
			//		Date[] secQmestre = quadrimestre(anno,Calendar.MAY);
			//		p1 = new JSONObject();
			//		p1.put(ID, 7);
			//		p1.put(DESCRIZIONE, "II Quadrimestre "+anno);		
			//		p1.put(DATA_DA, Costanti.dateFormat.format(secQmestre[0]));		
			//		p1.put(DATA_A, Costanti.dateFormat.format(secQmestre[1]));		
			//		periodi.put(p1);
			//		// terzo quadrimestre
			//		Date[] tQmestre = quadrimestre(anno,Calendar.SEPTEMBER);
			//		p1 = new JSONObject();
			//		p1.put(ID, 8);
			//		p1.put(DESCRIZIONE, "III Quadrimestre "+anno);		
			//		p1.put(DATA_DA, Costanti.dateFormat.format(tQmestre[0]));		
			//		p1.put(DATA_A, Costanti.dateFormat.format(tQmestre[1]));		
			//		periodi.put(p1);

			// primo semestre quadrimestre
			Date[] primoSemestre = semestre(annox,Calendar.JANUARY);
			p1 = new JSONObject();
			p1.put(ID, index++);
			p1.put(DESCRIZIONE, "I Semestre "+annox);		
			p1.put(DATA_DA, AppUtil.DATE_FORMAT.format(primoSemestre[0]));		
			p1.put(DATA_A, AppUtil.DATE_FORMAT.format(primoSemestre[1]));		
			periodi.put(p1);

			// secondo semestre quadrimestre
			Date[] secSemestre = semestre(annox,Calendar.JULY);
			p1 = new JSONObject();
			p1.put(ID, index++);
			p1.put(DESCRIZIONE, "II Semestre "+annox);		
			p1.put(DATA_DA, AppUtil.DATE_FORMAT.format(secSemestre[0]));		
			p1.put(DATA_A, AppUtil.DATE_FORMAT.format(secSemestre[1]));		
			periodi.put(p1);

			int[] mesi = { Calendar.JANUARY, Calendar.FEBRUARY,  Calendar.MARCH,  Calendar.APRIL,  Calendar.MAY,  Calendar.JUNE, 
					Calendar.JULY, Calendar.AUGUST,  Calendar.SEPTEMBER,  Calendar.OCTOBER,  Calendar.NOVEMBER,  Calendar.DECEMBER
			};


			SimpleDateFormat sdf = new SimpleDateFormat("MMMM", Locale.ITALY);
			
			for( int mese: mesi){
				int idx = index++;
				gc.setTimeInMillis(System.currentTimeMillis());
				gc.set(Calendar.YEAR, annox);
				p1 = new JSONObject();
				gc.set(Calendar.MONTH, mese);
				gc.set(Calendar.DAY_OF_MONTH, 1);
				p1.put(ID, idx);

				p1.put(DESCRIZIONE, sdf.format(gc.getTime())+" "+annox);		
				p1.put(DATA_DA, AppUtil.DATE_FORMAT.format(gc.getTime()));
				gc.set(Calendar.DAY_OF_MONTH, gc.getActualMaximum(Calendar.DAY_OF_MONTH));
				p1.put(DATA_A, AppUtil.DATE_FORMAT.format(gc.getTime()));		
				if(gcNow.get(Calendar.MONTH)==gc.get(Calendar.MONTH) && gcNow.get(Calendar.YEAR) == gc.get(Calendar.YEAR)){
					p1.put("SEL", true);		
					selected.put("mese", idx);
				}
				periodi.put(p1);
			}
			//rs.put(periodi);
		}

		JSONObject result = new JSONObject();
		result.put("results", periodi);
		result.put("selected", selected);

		return result.toString();
	}


	private Date[] semestre(int anno, int mese) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(Calendar.YEAR,anno);
		gc.set(Calendar.DATE, 1);
		gc.set(Calendar.MONTH,mese);

		Date d0 = gc.getTime();
		gc.add(Calendar.MONTH, 6);
		gc.add(Calendar.DATE, -1);
		Date d1 = gc.getTime();

		return new Date[]{d0,d1};
	}

//	private Date[] quadrimestre(int anno, int mese) {
//		GregorianCalendar gc = new GregorianCalendar();
//		gc.set(Calendar.YEAR,anno);
//		gc.set(Calendar.DATE, 1);
//		gc.set(Calendar.MONTH,mese);
//
//		Date d0 = gc.getTime();
//		gc.add(Calendar.MONTH, 4);
//		gc.add(Calendar.DATE, -1);
//		Date d1 = gc.getTime();
//
//		return new Date[]{d0,d1};
//	}

	private Date[] trimestre(int anno,int mese) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(Calendar.YEAR,anno);
		gc.set(Calendar.DATE, 1);
		gc.set(Calendar.MONTH,mese);

		Date d0 = gc.getTime();
		gc.add(Calendar.MONTH, 3);
		gc.add(Calendar.DATE, -1);
		Date d1 = gc.getTime();

		return new Date[]{d0,d1};
	}

}
