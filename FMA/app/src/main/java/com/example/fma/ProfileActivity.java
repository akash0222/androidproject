package com.example.fma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView schoolCardView, idCardView, libraryCardView, marksheetsCardView, aadhar, other;

    //firebase auth object
    private FirebaseAuth firebaseAuth;

    //view objects
    private TextView textViewUserEmail;
    private Button buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();

        schoolCardView = findViewById(R.id.schoolcardview);
        libraryCardView = findViewById(R.id.libraryCardView);
        idCardView = findViewById(R.id.id);
        marksheetsCardView = findViewById(R.id.marksheetCardView);
        aadhar=findViewById(R.id.aadhar);
        other = findViewById(R.id.other);

        schoolCardView.setOnClickListener(this);
        idCardView.setOnClickListener(this);
        libraryCardView.setOnClickListener(this);
        marksheetsCardView.setOnClickListener(this);
        aadhar.setOnClickListener(this);
        other.setOnClickListener(this);

        //if the user is not logged in
        //that means current user will return null
        if(firebaseAuth.getCurrentUser() == null){
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }

        //getting current user
        FirebaseUser user = firebaseAuth.getCurrentUser();

        //initializing views
        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);

        //displaying logged in user name
        textViewUserEmail.setText("Welcome "+user.getEmail());

        //adding listener to button
        buttonLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;

        switch (view.getId())
        {
            case R.id.schoolcardview:
            {
                intent = new Intent(this,Certificate.class);
                startActivity(intent);
                break;
            }
            case R.id.libraryCardView:
            {
                intent = new Intent(this,PhotoandSign.class);
                startActivity(intent);
                break;
            }
            case R.id.id:
            {
                intent = new Intent(this,Id.class);
                startActivity(intent);
                break;
            }
            case R.id.marksheetCardView:
            {
                intent = new Intent(this,Marksheets.class);
                startActivity(intent);
                break;
            }
            case R.id.aadhar:
            {
                intent = new Intent(this,Aadhar.class);
                startActivity(intent);
                break;
            }
            case R.id.other:
            {
                intent = new Intent(this,Other.class);
                startActivity(intent);
                break;
            }

        }
        //if logout is pressed
        if(view == buttonLogout){
            //logging out the user
            firebaseAuth.signOut();
            //closing activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }


    }
}
