package com.example.poline.barcode;


        import android.app.Dialog;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.text.Html;
        import android.text.method.LinkMovementMethod;
        import android.util.Log;
        import android.view.Gravity;
        import android.view.View;
        import android.webkit.WebView;
        import android.widget.Button;
        import android.widget.PopupWindow;
        import android.widget.RelativeLayout;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.zxing.Result;

        import me.dm7.barcodescanner.zxing.ZXingScannerView;

        import static com.example.poline.barcode.R.layout.activity_main;

public class main extends AppCompatActivity {

    private ZXingScannerView scannerView;
    private Button button;
    Dialog myDialog;
    public  static TextView textView2;
    public String resultCode = null;
    PopupWindow mpopup;
    public String finalText = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
    myDialog = new Dialog(this);
    textView2 = (TextView) findViewById(R.id.textView2);
    }
    public void showPopUp(View v){
        TextView txtclose;

        myDialog.setContentView(R.layout.infopopup);
        txtclose = (TextView) findViewById(R.id.txtclose);

        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });
        myDialog.show();
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

    public void view(View view) {

        View popUpView = getLayoutInflater().inflate(R.layout.infopopup,
                null); // inflating popup layout
        mpopup = new PopupWindow(popUpView, RelativeLayout.LayoutParams.FILL_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT, true); // Creation of popup
        mpopup.setAnimationStyle(android.R.style.Animation_Dialog);
        mpopup.showAtLocation(popUpView, Gravity.CENTER, 0, 0); // Displaying popup

        TextView some = (TextView) popUpView.findViewById(R.id.txtclose);
        some.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mpopup.dismiss();
            }
        });
        WebView web = popUpView.findViewById(R.id.result);
                web.loadUrl(finalText);
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
            finalText =" http://deus.co.ke/confirm.php?txtid_num=" +resultCode;
           // textView2.setText("http://nyeri.leja.co.ke/success.php?ref=" +resultCode);
            String text = " <a href='http://deus.co.ke/confirm.php?txtid_num=" + resultCode +" '> View Profile</a>";

            textView2.setText("View profile");

        }
    }

}