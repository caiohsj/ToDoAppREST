package br.ifms.exemplo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.IOException;

public class FormTodoActivity extends AppCompatActivity {
    RadioButton rbPrioridadeBaixa;
    RadioButton rbPrioridadeAlta;
    Button botaoSalvar;
    EditText campoDescricao;
    private TodoService todoService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_todo);
        inicializarReferencias();
        inicializarAcoes();
    }

    private void inicializarReferencias(){
        botaoSalvar = findViewById(R.id.activity_form_todo_botao_salvar);
        rbPrioridadeBaixa = findViewById(R.id.activity_form_todo_radio_prioridade_baixa);
        rbPrioridadeAlta = findViewById(R.id.activity_form_todo_radio_prioridade_alta);
        campoDescricao = findViewById(R.id.activity_form_todo_campo_descricao);
        todoService = new TodoService();
    }

    private void inicializarAcoes(){
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String descricao = campoDescricao.getText().toString();
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        Todo todo = new Todo();
                        todo.setDescricao(descricao);
                        if(rbPrioridadeBaixa.isChecked()) {
                            todo.setPrioridade("BAIXA");
                        } else if(rbPrioridadeAlta.isChecked()) {
                            todo.setPrioridade("ALTA");
                        }
                        try {
                            todoService.adicionar(todo);
                            finish();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}
