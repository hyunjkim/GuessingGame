package nyc.c4q.hyun.guess_the_number;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /*
    A player thinks of a number between 1 and 10000.
    Your program guesses a number and displays it in a TextView.
    The player responds by pressing one of two buttons to indicate whether their number is higher or lower.
    Your program makes a new guess and so on until it guesses the right number.
    Then extend your program so that the player can pick any positive number, not just ones up to 10000.
    */

    TextView textview;
    EditText editText;
    Button submitBttn;
    Button tooHighBtn;
    Button tooLowBtn;
    Button resetBtn;

    private String myNumber;
    private int mHighest = 10000;
    private int mLowest = 0;
    private int computer;
    private int input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display();

    }

    private void display() {
        textview = (TextView) findViewById(R.id.main_TV);
        editText = (EditText) findViewById(R.id.main_ET);
        submitBttn = (Button) findViewById(R.id.submitBtn);
        tooHighBtn = (Button) findViewById(R.id.toohigh_btn);
        tooLowBtn = (Button) findViewById(R.id.toolow_btn);
        resetBtn = (Button) findViewById(R.id.resetBtn);

        submitBttn.setOnClickListener(this);
        tooHighBtn.setOnClickListener(this);
        tooLowBtn.setOnClickListener(this);
        resetBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.submitBtn:
                myNumber = editText.getText().toString();
                input = Integer.valueOf(myNumber);
                submittedNum(input);
                break;
            case R.id.toohigh_btn:
                Log.d("toohigh", Integer.toString(mLowest));
                mLowest = computer ;
                computer = ((mHighest+mLowest)/2);
                compare(input, computer);
                textview.setText(String.valueOf(computer));
                break;
            case R.id.toolow_btn:
                mHighest = computer;
                Log.d("toolow", Integer.toString(mHighest));
                computer = ((mHighest+mLowest)/2);
                compare(input, computer);
                textview.setText(String.valueOf(computer));
                break;
            case R.id.resetBtn:
                mHighest = 10000;
                mLowest = 0;
                input = 0;
                editText.setText("");
                textview.setText("");
                break;
        }
    }

    public void submittedNum(int input) {
        computer = ((mHighest+mLowest)/2);
        textview.setText(String.valueOf(computer));
        compare(input,computer);
    }

    private void compare(int input, int computer) {
        if(input == computer){
            Toast.makeText(this, "You got it! ", Toast.LENGTH_SHORT).show();
            textview.setText(" ");
        } else {
            Toast.makeText(this, "Nope! ", Toast.LENGTH_SHORT).show();
        }
    }

}
