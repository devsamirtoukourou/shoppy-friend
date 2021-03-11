package com.example.shoppy_friend;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;

import android.view.LayoutInflater;  /* Ajout auto */

import android.view.View;

import android.view.ViewGroup;   /* Ajout auto */

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;  /* Ajout auto */

/* Début copie-colle de l'en-tête de Sam */

//package com.samuelvialle.loginwithfirebase.login;

/*import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.samuelvialle.loginwithfirebase.R;*/


/* Fin copie-colle de l'en-tête de Sam */

public class F07_New_Account_Fragment extends Fragment implements View.OnClickListener {
    @Override
    public void onClick(View v) {

    }

    /** Déclaration des variables globales **//*
    EditText user_name, user_surname, user_date_of_birth, user_po_box, user_email, user_mobile_number, user_password;
    RadioButton radio_btn_mr, radio_btn_ms;
    Button btn_register;

    *//** Variables Firebase **//*
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    *//** Méthode initUi pour la gestion du lien design code **//*
    public void initUI(){
        user_name= (EditText) findViewByIdfindViewById
        user_name = findViewById(R.id.user_name);
        user_email = findViewById(R.id.user_email);
        user_password = findViewById(R.id.user_password);
        user_avatar_profile = findViewById(R.id.user_avatar_profil);

        btn_register = findViewById(R.id.btn_register);

        btn_login_on_register_with_mail = findViewById(R.id.btn_login_on_register_with_mail);

        // Initialiasation de éléments clicables
        user_avatar_profile.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        btn_login_on_register_with_mail.setOnClickListener(this);

        // Initialisation de l'instance de Firebase
        mAuth = FirebaseAuth.getInstance();*/
    };


   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l02_register_with_mail);
        // Appel de la méthode initUI();
        initUI();
    }*/

/*

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_07_new_account, container, false);
    }
}*/


/**/

/*
public class L02_RegisterWithMail extends AppCompatActivity implements View.OnClickListener {

    *//** Déclaration des variables globales **//*
    EditText user_name, user_email, user_password;
    ImageView user_avatar_profile;
    Button btn_register;
    TextView btn_login_on_register_with_mail;
    Uri image_uri;

    *//** Variables Firebase **//*
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    *//** Méthode initUi pour la gestion du lien design code **//*
    public void initUI(){
        user_name = findViewById(R.id.user_name);
        user_email = findViewById(R.id.user_email);
        user_password = findViewById(R.id.user_password);
        user_avatar_profile = findViewById(R.id.user_avatar_profil);

        btn_register = findViewById(R.id.btn_register);

        btn_login_on_register_with_mail = findViewById(R.id.btn_login_on_register_with_mail);

        // Initialiasation de éléments clicables
        user_avatar_profile.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        btn_login_on_register_with_mail.setOnClickListener(this);

        // Initialisation de l'instance de Firebase
        mAuth = FirebaseAuth.getInstance();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l02_register_with_mail);
        // Appel de la méthode initUI();
        initUI();
    }

    *//** Ajout de la méthode onClick liée à l'implémentation de View.OnClickListener dans la classe **//*
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_register){ // Le bouton register
            RegisterUser(user_email.getText().toString(), user_password.getText().toString());
        } else if (v.getId() == R.id.btn_login_on_register_with_mail){ // Le bouton login sur la page register
            Intent intent = new Intent(L02_RegisterWithMail.this, L03_SignInWithMailAndPassword.class);
            // Récupération des données Mail et password pour ne pas avoir à les ré-écrire
            startActivity(intent);
        } else if (v.getId() == R.id.user_avatar_profil) { // Le bouton pour ajouter l'image de l'avatar
            SelectAvatarImg();
        }
    }

    *//** Gestion de l'ajout de l'avatar à partir de l'appareil photo ou de la galerie
     * Pour que cela fonctionne il faut ajouter les permissions dans le manifest **//*
    private void SelectAvatarImg() {
        // Les textes de l'alertDialog
        String add_photo = getResources().getString(R.string.add_photo);
        String take_photo = getResources().getString(R.string.take_photo);
        String from_gallery = getResources().getString(R.string.from_gallery);
        String cancel = getResources().getString(R.string.cancel);
        // Placer dans les options de l'alerte dialogue
        final CharSequence[] options = { take_photo, from_gallery, cancel  };
        // Création de l'alertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(L02_RegisterWithMail.this);
        // Ajout du titre
        builder.setTitle(add_photo);
        // Construction de l'alertDialog avec gestion du clic en utilisant les options
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                // Prendre une photo avec l'appareil photo
                if (options[item].equals(take_photo)){
                    // On vérifie que la version du terminal est supérieur à M (Marshmallow ;)
                    if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
                        // On vérifie si les permissions sont données si non on les affiche
                        if(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED ||
                                checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                            String [] permission = {Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};
                            requestPermissions(permission,1000);
                        }
                        else {
                            openCamera();
                        }
                    }
                    else {
                        openCamera();
                    }
                }
                else if (options[item].equals(from_gallery)){ // Affiche la galerie des photos du téléphone
                    Intent intent = new   Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);
                }
                else if (options[item].equals(cancel)) {
                    dialog.dismiss(); // On annule est on sort de l'alertDialog
                }
            }
        });
        builder.show();
    }

    *//** Gestion de l'ouverture de l'appareil photo **//*
    private void openCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE,"New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION,"From Camera");
        image_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);

        //Camera intent
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,image_uri);
        startActivityForResult(takePictureIntent, 1);
    }

    *//** Gestion de l'affichage du pop-up des autorisations du téléphone **//*
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1000:
                // Si les permissions sont accordées par l'utilisateur
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    // alors on ouvre l'appareil photo
                    openCamera();
                }
                else {
                    // Les permissions sont refusées par l'utilsateur --> on affiche un Toast
                    Toast.makeText(L02_RegisterWithMail.this,
                            getResources().getText(R.string.permission_denied),
                            Toast.LENGTH_LONG).show();
                }
        }
    }

    *//** Gestion du retour d'information en fonction de la provenance lors de l'ajout de l'avatar **//*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // Result code est RESULT_OK seulement si l'utilisateur ajoute une photo
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case 1:
                    // Si l'utilisateur ajoute une photo depuis la camera
                    user_avatar_profile.setImageURI(image_uri);
                    break;
                case 2:
                    // Si l'utilisateur ajoute la photo depuis la galerie
                    //data.getData retourne l'URI de l'image sélectionnée et l'affiche
                    image_uri = data.getData();
                    user_avatar_profile.setImageURI(image_uri);
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    *//** Gestion de l'ajout du nouvel utilisateur **//*
    private void RegisterUser(String email, String password) {
        if(email.equals("")){
            Toast.makeText(L02_RegisterWithMail.this, getResources().getText(R.string.enter_email),
                    Toast.LENGTH_SHORT).show();
        }
        else if(password.equals("")){
            Toast.makeText(L02_RegisterWithMail.this, getResources().getText(R.string.enter_password),
                    Toast.LENGTH_SHORT).show();
        }
        else if((user_name.getText().toString()).equals("")){
            Toast.makeText(L02_RegisterWithMail.this, getResources().getText(R.string.enter_name),
                    Toast.LENGTH_SHORT).show();
        }
        else if(image_uri == null){
            Toast.makeText(L02_RegisterWithMail.this, getResources().getText(R.string.select_avatar),
                    Toast.LENGTH_SHORT).show();
        }
        else {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                userProfile();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(L02_RegisterWithMail.this,
                                        getResources().getText(R.string.sign_up_failed) + task.getException().toString(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    *//** Enregistrement de l'avatar du profil dans Firebase **//*
    private void userProfile() {
        user = mAuth.getCurrentUser();
        if(user != null){
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName(user_name.getText().toString())
                    .setPhotoUri(image_uri).build();
            user.updateProfile(profileUpdates).addOnCompleteListener(this, new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    verifyEmailRequest();
                }
            });
        }
    }
    *//** Envoi de l'email de vérification sur l'adresse fourni par l'utilisateur **//*
    private void verifyEmailRequest() {
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(L02_RegisterWithMail.this,
                                    getResources().getText(R.string.sent_email)+ "\n"+user_email.getText().toString(),
                                    Toast.LENGTH_LONG).show();
                            mAuth.signOut();
                            startActivity(new Intent(L02_RegisterWithMail.this, L03_SignInWithMailAndPassword.class));
                        }
                        else {
                            Toast.makeText(L02_RegisterWithMail.this,
                                    getResources().getText(R.string.sign_up_okl_but_email_not_sent),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
*/
