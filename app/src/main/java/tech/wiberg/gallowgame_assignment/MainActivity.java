package tech.wiberg.gallowgame_assignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private TextView guessedWordsTitleTextView;
    private TextView guessWordsTextView;
    private TextView wordToGuessTextView;
    private EditText letterTextEdit;
    private ImageView imageView;
    private Button guessBtn;

    private GallowLogic game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        guessedWordsTitleTextView = (TextView) findViewById(R.id.guessedWordsTitleTextView);
        guessWordsTextView = (TextView) findViewById(R.id.guessedWordsTextView);
        wordToGuessTextView = (TextView) findViewById(R.id.wordToGuessTextView);

        letterTextEdit = (EditText) findViewById(R.id.letterTextEdit);

        imageView = (ImageView) findViewById(R.id.imageView);

        guessBtn = (Button) findViewById(R.id.guessBtn);

        game = new GallowLogic();

        initializeValues();
    }

    private void initializeValues() {
        guessedWordsTitleTextView.setText(this.getResources().getString(R.string.guessed_words_tv));
        guessWordsTextView.setText("");
        wordToGuessTextView.setText(this.getResources().getString(R.string.word_to_guess_tv));

        letterTextEdit.setText(this.getResources().getString(R.string.guess_letter_te));

        imageView.setImageDrawable(this.getDrawable(R.drawable.gallow));

        guessBtn.setText(this.getResources().getString(R.string.guess_word_btn));
        guessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guessClickBtn();
            }
        });
    }

    private void guessClickBtn() {
        System.out.println("CLICKED!!!!!");
        game.guess(letterTextEdit.getText().toString());
        guessWordsTextView.setText(game.getUsedLetters().toString());
        game.logStatus();

        if (game.getWrongLettersCount() > 0) {
            switch (game.getWrongLettersCount()) {
                case 1: imageView.setImageDrawable(this.getResources().getDrawable(R.drawable.wrong1)); break;
                case 2: imageView.setImageDrawable(this.getResources().getDrawable(R.drawable.wrong2)); break;
                case 3: imageView.setImageDrawable(this.getResources().getDrawable(R.drawable.wrong3)); break;
                case 4: imageView.setImageDrawable(this.getResources().getDrawable(R.drawable.wrong4)); break;
                case 5: imageView.setImageDrawable(this.getResources().getDrawable(R.drawable.wrong5)); break;
                case 6: imageView.setImageDrawable(this.getResources().getDrawable(R.drawable.wrong6)); break;
                default: imageView.setImageDrawable(this.getResources().getDrawable(R.drawable.gallow));
            }
        }

        letterTextEdit.setText("");

        wordToGuessTextView.setText("Word: " + game.getVisibleWord());

        if (game.isGameOver()) {
            gameOver();
        }
    }

    private void gameOver() {
        initializeValues();

        imageView.setImageDrawable(this.getDrawable(R.drawable.gallow));
        String gameOverStr = "Game over! You ";
        if (game.isGameWon()) gameOverStr += "won ";
        else gameOverStr += "lost ";
        gameOverStr += "the game. The correct word was: " + game.getWord();
        wordToGuessTextView.setText(gameOverStr);

        game = new GallowLogic();
    }
}
