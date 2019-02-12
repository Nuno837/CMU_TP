package pt.ipp.estg.cmu_tp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import pt.ipp.estg.cmu_tp.Models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class UserActivity extends AppCompatActivity {
    private TextView tvUser;

    private Button button;
    private Button button2;
    private Button button3;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        user = (User) getIntent().getSerializableExtra("User");

        tvUser = findViewById(R.id.tvUser);

        if (user != null) {
            tvUser.setText("Bem-Vindo "+user.getName() +" "+user.getLastName());

        }
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserActivity.this, MapsActivity.class));
            }
        });

        button2 = findViewById(R.id.show_POI);

        button2.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserActivity.this, POI_info.class));
            }
        });

        button3 = findViewById(R.id.btCarregamentos);

        button3.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserActivity.this, HistoricoActivity.class));
            }
        });
    }

}
