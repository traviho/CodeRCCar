package com.example.travisho.blockly;

import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.blockly.android.AbstractBlocklyActivity;
import com.google.blockly.android.BlocklyActivityHelper;
import com.google.blockly.android.codegen.CodeGenerationRequest;
import com.google.blockly.android.codegen.LoggingCodeGeneratorCallback;

import org.mozilla.javascript.Function;
import org.mozilla.javascript.RhinoException;
import org.mozilla.javascript.Scriptable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Simplest implementation of AbstractBlocklyActivity.
 */
public class BlocklyActivity extends AbstractBlocklyActivity {
    private static final String TAG = "SimpleActivity";
    public static Bluetooth bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        try {
            bt = new Bluetooth(this, mHandler);
            connectService();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void connectService(){
        try {
            BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (bluetoothAdapter.isEnabled()) {
                bt.start();
                bt.connectDevice("HC-06");
                Log.d("myTag", "Btservice started - listening");
            } else {
                Log.w("myTag", "Btservice started - bluetooth is not enabled");
            }
        } catch(Exception e){
            Log.e("myTag", "Unable to start bt ",e);
        }
    }

    private static final List<String> BLOCK_DEFINITIONS = Arrays.asList(
            "kodactive/kodactive_blocks.json",
            "default/logic_blocks.json",
            "default/loop_blocks.json",
            "default/math_blocks.json",
            "default/variable_blocks.json",
            "default/colour_blocks.json",
            "default/text_blocks.json"
    );
    private static final List<String> JAVASCRIPT_GENERATORS = Arrays.asList(
            "kodactive/generators.js"
    );

    private final Handler mHandler = new Handler();

    private final CodeGenerationRequest.CodeGeneratorCallback mCodeGeneratorCallback =
            new CodeGenerationRequest.CodeGeneratorCallback() {
                @Override
                public void onFinishCodeGeneration(final String generatedCode) {
                    System.out.println("Blockly Code: " + generatedCode);
                    Toast.makeText(getApplicationContext(), generatedCode,
                            Toast.LENGTH_LONG).show();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            String result = "";
                            String before = "function Cars() {\n" +
                                    "this.commands = \"\";\n" +
                                    "    this.moveForward = function () {\n" +
                                    "        this.commands += \"1\";\n" +
                                    "    }\n" +
                                    "    this.moveBackward = function () {\n" +
                                    "        this.commands += \"2\";\n" +
                                    "    }\n" +
                                    "    this.turnLeft = function () {\n" +
                                    "        this.commands += \"3\";\n" +
                                    "    }\n" +
                                    "    this.turnRight = function () {\n" +
                                    "        this.commands += \"4\";\n" +
                                    "    }\n" +
                                    "    this.rightDonut = function () {\n" +
                                    "        this.commands += \"5\";\n" +
                                    "    }\n" +
                                    "    this.leftDonut = function () {\n" +
                                    "        this.commands += \"6\";\n" +
                                    "    }\n" +
                                    "}\n" +
                                    "function move() {\n" +
                                    "var Car = new Cars();";

                            String encoded = JavaScriptUtil.makeJsString(generatedCode);
                            if (generatedCode.contains("front")) {
                                try {
                                    bt.sendMessage("5");
                                } catch (NullPointerException e){
                                    e.printStackTrace();
                                }
                            } else {
                                encoded.replaceAll("\"", "");
                                Toast.makeText(BlocklyActivity.this, generatedCode, Toast.LENGTH_SHORT).show();
                                // Rhino Code
                                String finalCode = before + generatedCode + "\n" + "return Car.commands;\n" +
                                        "}";

                                System.out.println(generatedCode);

                                Object[] params = new Object[]{"javaScriptParam"};
                                org.mozilla.javascript.Context rhino = org.mozilla.javascript.Context.enter();
                                rhino.setOptimizationLevel(-1);

                                try {
                                    Scriptable scope = rhino.initStandardObjects();
                                    rhino.evaluateString(scope, finalCode, "JavaScript", 1, null);
                                    Function function = (Function) scope.get("move", scope);
                                    result = function.call(rhino, scope, scope, params).toString();
                                    //  bt.sendMessage(result);
                                    System.out.println("YO BITCH " + result);
                                    ArrayList<Integer> bluetoothCommands = new ArrayList<Integer>();
                                    for (int i = 0; i < result.length(); i++) {
                                        bluetoothCommands.add(Integer.parseInt("" + result.charAt(i)));
                                    }

                                    SendToArduino arduino = new SendToArduino(bluetoothCommands, bt);
                                    arduino.run();

                                } catch (RhinoException e) {
                                    e.printStackTrace();
                                } finally {
                                    org.mozilla.javascript.Context.exit();
                                }
                                ArrayList<Integer> bluetoothCommands = new ArrayList<Integer>();
                                for (int i = 0; i < result.length(); i++) {
                                    bluetoothCommands.add(Integer.parseInt("" + result.charAt(i)));
                                }

                            }
                        }
                    });
                }
            };

    @Override
    protected View onCreateContentView(int containerId) {
        View root = getLayoutInflater().inflate(R.layout.modified_blockly_unified_workspace, null);

        return root;
    }

    @Override
    protected BlocklyActivityHelper onCreateActivityHelper() {
        return new ModifiedBlocklyActivityHelper(this);
    }

    @NonNull
    @Override
    protected List<String> getBlockDefinitionsJsonPaths() {
        return BLOCK_DEFINITIONS;
    }

    @NonNull
    @Override
    protected String getToolboxContentsXmlPath() {
        return "kodactive/stage1.xml";
    }

    @NonNull
    @Override
    protected List<String> getGeneratorsJsPaths() {
        return JAVASCRIPT_GENERATORS;
    }

    @NonNull
    @Override
    protected CodeGenerationRequest.CodeGeneratorCallback getCodeGenerationCallback() {
        // Uses the same callback for every generation call.
        return mCodeGeneratorCallback;
    }

    public void doRunCode(){
        onRunCode();
    }
}
