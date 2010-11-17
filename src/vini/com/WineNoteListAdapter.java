package vini.com;


import java.util.Calendar;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WineNoteListAdapter extends BaseAdapter {
	private List <WineNote> wnList;
	private Context context;
	
	public WineNoteListAdapter(List<WineNote> wna,
							   Context context) {
		this.context = context;
		this.wnList = wna;
	}
	
	public int getCount() {
		return wnList.size();
	}

	public Object getItem(int position) {
		return wnList.get(position);
	}

	public long getItemId(int position) {
		return wnList.get(position).getId();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
	      LinearLayout itemLayout;

	      WineNote wn = wnList.get(position);
	      itemLayout= (LinearLayout) LayoutInflater.from(context)
	                   .inflate(R.layout.wine_note_list_item, parent, false);
	      TextView tvWineName = (TextView) itemLayout.findViewById(R.id.wine_name);
	      tvWineName.setText(wn.getName());

	      TextView tvWineYear = (TextView) itemLayout.findViewById(R.id.wine_year);
	      tvWineYear.setText(String.valueOf(wn.getYear()));
	      
	      TextView tvWineTasteDate = (TextView) itemLayout.findViewById(R.id.wine_taste_date);
	      Calendar tasteDate = wn.getTasteDate();
	      String dateString = tasteDate.get(Calendar.YEAR) + "-" +
	      					  tasteDate.get(Calendar.MONTH) + "-" +
	      					  tasteDate.get(Calendar.DAY_OF_MONTH);
	      
	      tvWineTasteDate.setText(dateString);
	      
	      ImageView ivWineGrade = (ImageView) itemLayout.findViewById(R.id.wine_grade);
	      switch(wn.getGrade()){
	      case 1:
	    	  ivWineGrade.setImageResource(R.drawable.grade1);
	    	  break;
	      case 2:
	    	  ivWineGrade.setImageResource(R.drawable.grade2);
	    	  break;
	      case 3:
	    	  ivWineGrade.setImageResource(R.drawable.grade3);
	    	  break;
	      case 4:
	    	  ivWineGrade.setImageResource(R.drawable.grade4);
	    	  break;
	      case 5:
	    	  ivWineGrade.setImageResource(R.drawable.grade5);
	    	  break;
	      default:
	    	  ivWineGrade.setImageResource(R.drawable.grade0);
	    	  break;
	      }
	      
	      
	      TextView tvWineComment = (TextView) itemLayout.findViewById(R.id.wine_comment);
	      tvWineComment.setText(wn.getComment());

	      return itemLayout;
	}

}
