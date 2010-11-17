package vini.com;

import java.util.Calendar;
import java.util.GregorianCalendar;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.app.DatePickerDialog;

public class WineNoteDialog extends Dialog 
 implements Button.OnClickListener, DatePickerDialog.OnDateSetListener {

    public interface ReadyListener {
        public void ready(WineNote wn);
    }

	private static final int MAX_YEARS_OLD = 50;

    private ReadyListener readyListener;
    private EditText etName;
    private Spinner spYear;
    private TextView tvDate;
    private Spinner spGrade;
    private EditText etComment;
    private WineNote wineNote;
    private Button tasteDateButton;
	private Button buttonSave;
	private Button buttonCancel;
	private Context context;
	private Calendar tasteDate;

    public WineNoteDialog(Context context,
            	ReadyListener readyListener) {
    	super(context);
    	this.context = context;
    	this.readyListener = readyListener;
    	this.wineNote = null;
    }

    public void setWineNote(WineNote wn){
    	wineNote = wn;
    }
    
    private void showDateDialog() {
    	int cyear = tasteDate.get(Calendar.YEAR);
    	int cmonth = tasteDate.get(Calendar.MONTH);
    	int cday = tasteDate.get(Calendar.DAY_OF_MONTH);
    	// TODO getOwnerActivity doesn't work the way I thought
    	// (or I have done something wrong)
    	DatePickerDialog dpd = new DatePickerDialog(// getOwnerActivity(),  
    												this.context,
    												this,
    												cyear, cmonth, cday);
    	dpd.show();
    }
    
    public void onStart(){
    	if(wineNote != null)
    	{
    		etName.setText(wineNote.getName());
    		
    		tasteDate = wineNote.getTasteDate();
    		String dateString = tasteDate.get(Calendar.YEAR) + "-" +
    			tasteDate.get(Calendar.MONTH) + "-" +
    			tasteDate.get(Calendar.DAY_OF_MONTH);
    		tvDate.setText(dateString);

    		etComment.setText(wineNote.getComment());

    		spGrade.setSelection(wineNote.getGrade());
    		
        	Calendar c = Calendar.getInstance();
        	int cyear = c.get(Calendar.YEAR);
        	if((cyear-wineNote.getYear()) < MAX_YEARS_OLD){
        		spYear.setSelection(cyear-wineNote.getYear());        		
        	}
        	else{
        		spYear.setSelection(0);
        	}	
    	}
    	else
    	{
    		etName.setText("NULL!");
    	}
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wine_note_dialog);
        setTitle("Edit Wine Note");

        buttonSave = (Button)findViewById(R.id.wine_note_save);
        buttonSave.setOnClickListener(this);
        
        buttonCancel = (Button)findViewById(R.id.wine_note_cancel);
        buttonCancel.setOnClickListener(this);
        
        etName = (EditText) findViewById(R.id.wine_note_name);
        
        tvDate = (TextView) findViewById(R.id.wine_note_taste_date);
    	tasteDateButton = 
    		(Button)findViewById(R.id.wine_note_taste_date_set);
    	tasteDateButton.setOnClickListener(this);

        spYear = (Spinner) findViewById(R.id.wine_note_year);
    	ArrayAdapter<Integer> adapterInt = new ArrayAdapter<Integer>(context,
        		android.R.layout.simple_spinner_item);

        Calendar c = Calendar.getInstance();
        // This is not a very bullet proof way to handle the dates
        // update to something more rigid
    	int cyear = c.get(Calendar.YEAR);
    	int i = cyear;
        while(i > (cyear-MAX_YEARS_OLD)){
        	adapterInt.add(i);
        	i--;
        }

        spYear = (Spinner) findViewById(R.id.wine_note_year);
        spYear.setAdapter(adapterInt);

        spGrade = (Spinner) findViewById(R.id.wine_note_grade_selector);
        ArrayAdapter<CharSequence> adapterCh = ArrayAdapter.createFromResource(
                context, R.array.wine_grades_array, android.R.layout.simple_spinner_item);
        adapterCh.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGrade.setAdapter(adapterCh);

        etComment = (EditText) findViewById(R.id.wine_note_comment);
    }

    public void onClick(View v) {
    	if(v == tasteDateButton){
    		showDateDialog();
        }
    	else if(v == buttonSave){
        	// Fill in Wine Note
        	wineNote.setComment(String.valueOf(etComment.getText()));
        	wineNote.setGrade(spGrade.getSelectedItemPosition());
        	wineNote.setName(String.valueOf(etName.getText()));
        	wineNote.setTasteDate(tasteDate);
        	wineNote.setYear((Integer)spYear.getSelectedItem());
            readyListener.ready(wineNote);
            this.dismiss();    		
    	}
    	else if(v == buttonCancel){
            this.dismiss();
    	}
    }

	public void onDateSet(DatePicker dp, int year,
			              int month, int day) {
		tasteDate = new GregorianCalendar(year, month, day);
		String dateString = tasteDate.get(Calendar.YEAR) + "-" +
		                    tasteDate.get(Calendar.MONTH) + "-" +
		                    tasteDate.get(Calendar.DAY_OF_MONTH);

        tvDate.setText(dateString);
	}
}
