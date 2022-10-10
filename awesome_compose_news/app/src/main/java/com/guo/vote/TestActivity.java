package com.guo.vote;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.ComponentActivity;

import com.compose.awsome.news.R;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;

/**
 * Main Ethereum Network
 * https://mainnet.infura.io/[your-token]
 * <p>
 * Test Ethereum Network (sepolia)
 * https://sepolia.infura.io/v3/[your-token]
 * <p>
 * Test Rinkeby Network
 * https://rinkeby.infura.io/[your-token]
 * <p>
 * IPFS Gateway
 * https://ipfs.infura.io
 * <p>
 * IPFS RPC
 * https://ipfs.infura.io:5001
 */
public class TestActivity extends ComponentActivity {

//    private final static String privateKeyRopsten = "YOUR_PRIVATE_KEY";
    private final static String privateKeyRopsten = "8c74e439b75502e9e8c45f77bb2953a68ac4a2b34238f50c089fe09cc2ebc0cf";
    //etherscan查看合约https://ropsten.etherscan.io/address/0x024b64940518779068e57352F3bDDdE08E4D9c40
//    private final static String voteContractAddressRopsten = "0xae8957E7e238Bfae1985BE4862Ccf6394207F886";
    private final static String nameContractAddressRopsten = "0xa0Ffb4675f43C106A06D5DF547C6a2CfB6D7643E";
//    private final static String ropstenUrl = "https://ropsten.infura.io/YOUR_API_KEY";
    private final static String ropstenUrl = "https://sepolia.infura.io/v3/8b3ff236c8cd45939cc8b580dc9cac03";

    private ProgressBar progressBar;
    private EditText editText;
    private TextView greetingTextView;
    private TextView gasPriceTextView;
    private TextView gasLimitTextView;
    private SeekBar gasPriceSeekBar;

    private Web3j web3j;

    private Credentials credentials = Credentials.create(privateKeyRopsten);
    private int minimumGasLimit = 21000;
    private BigInteger gasLimit = new BigInteger(String.valueOf(minimumGasLimit));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
        initUi();
        setGasPriceText(10);
        setGasLimit(minimumGasLimit);
        initWeb3j();
    }

    private void initUi() {
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        editText = (EditText) findViewById(R.id.edit_text);
        greetingTextView = (TextView) findViewById(R.id.text);
        Button readButton = (Button) findViewById(R.id.button);
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getGreeting();
            }
        });
        Button writeButton = (Button) findViewById(R.id.write_button);
        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeGreetingToContract();
            }
        });
        gasPriceSeekBar = (SeekBar) findViewById(R.id.gas_price_seek_bar);
        gasPriceSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setGasPriceText(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        SeekBar gasLimitSeekBar = (SeekBar) findViewById(R.id.gas_limit_seek_bar);
        gasLimitSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setGasLimit(progress + minimumGasLimit);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        gasLimitTextView = (TextView) findViewById(R.id.gas_limit_text_view);
        gasPriceTextView = (TextView) findViewById(R.id.gas_price_text_view);
    }

    private void writeGreetingToContract() {
        progressBar.setVisibility(View.VISIBLE);
        WriteTask writeTask = new WriteTask();
        writeTask.execute(editText.getText().toString());
    }

    private void getGreeting() {
        try {
            progressBar.setVisibility(View.VISIBLE);
            ReadTask readTask = new ReadTask();
            readTask.execute();
        } catch (Exception e) {
            Log.d("wat", "getGreeting exception = " + e.getMessage());
        }
    }

    private void initWeb3j() {
        InitWeb3JTask task = new InitWeb3JTask();
        task.execute(ropstenUrl);
    }

    public void setGasPriceText(int gasPrice) {
        String formattedString = getString(R.string.gas_price, String.valueOf(gasPrice));
        gasPriceTextView.setText(formattedString);
    }

    private BigInteger getGasPrice() {
        int gasPriceGwei = gasPriceSeekBar.getProgress();
        BigInteger gasPriceWei = BigInteger.valueOf(gasPriceGwei + 1000000000L);
        Log.d("wat", "getGasPrice: " + String.valueOf(gasPriceGwei));
        return gasPriceWei;
    }

    public void setGasLimit(int gasLimit) {
        String gl = String.valueOf(gasLimit);
        this.gasLimit = new BigInteger(gl);
        gasLimitTextView.setText(getString(R.string.gas_limit, gl));
    }

    private class ReadTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String result;
            try {
                Test test = Test.load(nameContractAddressRopsten, web3j, credentials, getGasPrice(), gasLimit);
                result = test.getName().send();
//                Vote vote = Vote.load(greeterContractAddressRopsten, web3j, credentials, getGasPrice(), gasLimit);
//                result = vote.proposalA().send().toString();
            } catch (Exception e) {
                result = "Error reading the smart contract. Error: ";
            }

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressBar.setVisibility(View.INVISIBLE);
            greetingTextView.setText(result);
        }
    }


    private class WriteTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String greetingToWrite = params[0];

            String result;
            try {
//                Vote vote = Vote.load(greeterContractAddressRopsten, web3j, credentials, getGasPrice(), gasLimit);
//                TransactionReceipt transactionReceipt = vote.vote(new BigInteger("1")).send();
                Test test = Test.load(nameContractAddressRopsten, web3j, credentials, getGasPrice(), gasLimit);
                TransactionReceipt transactionReceipt = test.setName(greetingToWrite).send();
                result = "Successful transaction. Gas used: " + transactionReceipt.getGasUsed();
            } catch (Exception e) {
                result = "Error during transaction. Error: " + e.getMessage();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(TestActivity.this, result, Toast.LENGTH_LONG).show();
        }
    }

    private class InitWeb3JTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String url = params[0];
            String result = "Success initializing web3j/infura";
            try {
                web3j = Web3j.build(new HttpService(url));
            } catch (Exception wtf) {
                String exception = wtf.toString();
                Log.d("wat", "Error initializing web3j/infura. Error: " + exception);
            }

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Toast.makeText(TestActivity.this, result, Toast.LENGTH_LONG).show();
        }
    }
}
