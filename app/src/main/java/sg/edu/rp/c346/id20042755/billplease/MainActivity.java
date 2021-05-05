    package sg.edu.rp.c346.id20042755.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ToggleButton;
import android.widget.TextView;

import org.w3c.dom.Text;


    public class MainActivity extends AppCompatActivity {

        EditText amtpax;
        EditText numpax;
        ToggleButton svs;
        ToggleButton gst;
        EditText discount;
        RadioGroup typePayment;
        TextView finalprice;
        TextView paymenteach;
        Button reset;
        Button split;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            amtpax = findViewById(R.id.AmountInput);
            numpax = findViewById(R.id.PaxInput);
            svs = findViewById(R.id.NoSVSBtn);
            gst = findViewById(R.id.GstBtn);
            discount = findViewById(R.id.DiscountInput);
            typePayment = findViewById(R.id.Radiogroup);
            finalprice = findViewById(R.id.FinalPayment);
            paymenteach = findViewById(R.id.PaymentEach);
            reset = findViewById(R.id.ResetBtn);
            split = findViewById(R.id.SplitBtn);
            split.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    {
                        String userInput = amtpax.getText().toString();
                        String userInput2 = numpax.getText().toString();
                        String userInput3 = discount.getText().toString();
                        if (userInput.trim().length() != 0 && userInput.trim().length() != 0) {
                            double newAmount = 0.0;

                            if (!gst.isChecked() && !svs.isChecked()) {
                                newAmount = Double.parseDouble(userInput);
                            } else if (!svs.isChecked() && gst.isChecked()) {
                                newAmount = Double.parseDouble(userInput) * 1.07;
                            } else if (!gst.isChecked() && svs.isChecked()) {
                                newAmount = Double.parseDouble(userInput) * 1.10;
                            } else {
                                newAmount = Double.parseDouble(userInput) * 1.17;
                            }

                            if (userInput3.trim().length() != 0) {
                                newAmount = 1 - Double.parseDouble(userInput3) / 100;
                            }

                            String finalSum = String.format("%2.f", newAmount);
                            finalprice.setText("Total Bill is $" + finalSum);


                            int checkRadio = typePayment.getCheckedRadioButtonId();
                            int numPax = Integer.parseInt(String.valueOf(amtpax));
                            String output = String.format("%.2f", newAmount / numPax);

                            if (numPax != 1 && checkRadio == R.id.CashBtn) {
                                paymenteach.setText("Each pays: $" + output + "in cash");
                            } else if (numPax != 1 && checkRadio == R.id.PaynowBtn) {
                                paymenteach.setText("Each pays: $" + output + "via Paynow to 92349104");

                            }


                        }
                        split.setOnClickListener(new View.OnClickListener() {


                            @Override
                            public void onClick(View v) {

                                amtpax.setText("");
                                numpax.setText("");
                                svs.setText("");
                                gst.setText("");
                                discount.setText("");

                            }
                        });

                    }


                }


            });
        }
    }