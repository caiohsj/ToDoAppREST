package br.ifms.exemplo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private TodoAdapter adapter;
    private List<Todo> todos;
    private TodoService todoService;
    private FloatingActionButton botaoAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        todoService = new TodoService();
        inicializarListView();
        inicializarBotoes();
        inicializarAcoes();

        /*
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                Todo todo = new Todo();
                todo.setDescricao("Ir na padaria");
                todo.setPrioridade("BAIXA");
                try {
                    todoService.adicionar(todo);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }); */
    }

    public void inicializarListView(){
        listView = findViewById(R.id.main_activity_listview);
        todos = new ArrayList<Todo>();
        adapter = new TodoAdapter(this, todos);
        listView.setAdapter(adapter);
    }

    private void inicializarBotoes(){
        botaoAdd = findViewById(R.id.activity_list_tarefas_botao_nova_tarefa);
    }

    private void inicializarAcoes(){
        botaoAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,FormTodoActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        AsyncTask.execute(new Runnable(){
            @Override
            public void run() {
                try {
                    todos.clear();
                    todos.addAll(todoService.listar());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        //t.start();
    }
}
