package com.example.poline.barcode;


        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.text.Html;
        import android.text.method.LinkMovementMethod;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.zxing.Result;

        import me.dm7.barcodescanner.zxing.ZXingScannerView;

        import static com.example.poline.barcode.R.layout.activity_main;

public class main extends AppCompatActivity {

    private ZXingScannerView scannerView;
    private Button button;

    public  static TextView textView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);

    textView2 = (TextView) findViewById(R.id.textView2);
    }
    public void scanCode(View view){
        scannerView = new ZXingScannerView(this);
        scannerView.setResultHandler(new ZXingScannerResultHandler());

        setContentView(scannerView);
        scannerView.startCamera();
    }
    @Override
    public void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }
    class ZXingScannerResultHandler implements ZXingScannerView.ResultHandler
    {

        @Override
        public void handleResult(Result result) {

            setContentView(activity_main);
            TextView textView2;
            textView2 = (TextView)findViewById(R.id.textView2);
            textView2.setClickable(true);
            textView2.setMovementMethod(LinkMovementMethod.getInstance());

           String resultCode = result.getText().toString();
            //Toast.makeText(main.this, "http://nyeri.leja.co.ke/success.php?ref=" +resultCode, Toast.LENGTH_LONG);
            scannerView.stopCamera();
            String finalText =" <a href='http://deus.co.ke/confirm.php?txtid_num=" +resultCode;
           // textView2.setText("http://nyeri.leja.co.ke/success.php?ref=" +resultCode);
            String text = " <a href='http://deus.co.ke/confirm.php?txtid_num=" + resultCode +" '> View Profile</a>";

            textView2.setText(Html.fromHtml(text));

        }
    }

}