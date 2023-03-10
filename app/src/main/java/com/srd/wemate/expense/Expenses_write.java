package com.srd.wemate.expense;

import static com.srd.wemate.MainActivity.user_id;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.srd.wemate.MainActivity;
import com.srd.wemate.ProfileActivity;
import com.srd.wemate.R;
import com.srd.wemate.retrofit.ExpenseApi;
import com.srd.wemate.retrofit.RetrofitService;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Expenses_write extends AppCompatActivity{
    Spinner spinner;
    private TextView textView_Date;
    private DatePickerDialog.OnDateSetListener callbackMethod;
    EditText money,memo;
    Spinner purpose;
    Button ok_btn;
    String purpose_str,date_str,memo_str;
    int money_int;
    TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses_write);
        money = (EditText) findViewById(R.id.money);
        purpose = (Spinner)findViewById(R.id.spinner);
        date = (TextView) findViewById(R.id.textView_date);
        memo = (EditText) findViewById(R.id.memo);
        ok_btn = (Button) findViewById(R.id.button6);


        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                money_int = Integer.parseInt(money.getText().toString());
                purpose_str = purpose.getSelectedItem().toString();
                date_str = date.getText().toString();
                memo_str = memo.getText().toString();
                Log.i("","생활비-->"+money_int+"-"+purpose_str+"-"+date_str+"-"+memo_str);
                RetrofitService retrofitService = new RetrofitService();
                ExpenseApi expenseApi = retrofitService.getRetrofit().create(ExpenseApi.class);

                ExpenseData expense = new ExpenseData();
                expense.setUid(user_id);
                expense.setMoney(money_int);
                expense.setPurpose(purpose_str);
                expense.setDate(date_str);//expense.setDate(date_str);
                expense.setMemo(memo_str);

                expenseApi.save(expense).enqueue(new Callback<ExpenseData>() {
                    @Override
                    public void onResponse(Call<ExpenseData> call, Response<ExpenseData> response) {
                        Log.i("save","Expense Response 성공");
                    }

                    @Override
                    public void onFailure(Call<ExpenseData> call, Throwable t) {
                        Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE,"에러",t);

                    }
                });

                Intent intent = new Intent(getApplicationContext(), Expense.class);
                startActivity(intent);

            }
        });

        spinner = findViewById(R.id.spinner);
        ArrayAdapter moneyAdapter = ArrayAdapter.createFromResource(this, R.array.money_array, android.R.layout.simple_spinner_dropdown_item);
        moneyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(moneyAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }
            @Override public void onNothingSelected(AdapterView<?> parent) { }
        });

        this.InitializeView();
        this.InitializeListener();
    }

    public void InitializeView()
    {
        textView_Date = (TextView)findViewById(R.id.textView_date);
    }

    public void InitializeListener()
    {
        callbackMethod = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                textView_Date.setText(year + "년" + monthOfYear + "월" + dayOfMonth + "일");
            }
        };
    }

    public void OnClickHandler(View view)
    {
        DatePickerDialog dialog = new DatePickerDialog(this, callbackMethod, 2022, 5, 21);

        dialog.show();
    }

}