
'use strict';

Blockly.JavaScript['car_forward'] = function(block) {
  // Generate JavaScript for moving forward or backwards.
  return 'Car.moveForward();\n'
};

Blockly.JavaScript['car_forward_param'] = function(block) {
  // Generate JavaScript for moving forward or backwards.
  var value = Blockly.JavaScript.valueToCode(block, 'VALUE',
      Blockly.JavaScript.ORDER_NONE) || '0';
  return 'Car.moveForward(' + value + ', \'block_id_' + block.id + '\');\n';
};

Blockly.JavaScript['car_backward'] = function(block) {
  // Generate JavaScript for moving forward or backwards.
  return 'Car.moveBackward();\n'
};

Blockly.JavaScript['car_backward_param'] = function(block) {
  // Generate JavaScript for moving forward or backwards.
  var value = Blockly.JavaScript.valueToCode(block, 'VALUE',
      Blockly.JavaScript.ORDER_NONE) || '0';
  return 'Car.moveBackward(' + value + ', \'block_id_' + block.id + '\');\n';
};

Blockly.JavaScript['car_right'] = function(block) {
  // Generate JavaScript for moving forward or backwards.
  return 'Car.turnRight();\n'
};

Blockly.JavaScript['car_right_param'] = function(block) {
  // Generate JavaScript for moving forward or backwards.
  var value = Blockly.JavaScript.valueToCode(block, 'VALUE',
      Blockly.JavaScript.ORDER_NONE) || '0';
  return 'Car.turnRight(' + value + ', \'block_id_' + block.id + '\');\n';
};

Blockly.JavaScript['car_left'] = function(block) {
  // Generate JavaScript for moving forward or backwards.
  return 'Car.turnLeft();\n'
};

Blockly.JavaScript['car_left_param'] = function(block) {
  // Generate JavaScript for moving forward or backwards.
  var value = Blockly.JavaScript.valueToCode(block, 'VALUE',
      Blockly.JavaScript.ORDER_NONE) || '0';
  return 'Car.turnLeft(' + value + ', \'block_id_' + block.id + '\');\n';
};

Blockly.JavaScript['left_donut'] = function(block) {
  // Generate JavaScript for moving forward or backwards.
  return 'Car.turnLeft();\n'
};

Blockly.JavaScript['right_donut'] = function(block) {
  // Generate JavaScript for moving forward or backwards.
  return 'Car.turnRight();\n'
};

/**
 * The generated code for turtle blocks includes block ID strings.  These are useful for
 * highlighting the currently running block, but that behaviour is not supported in Android Blockly
 * as of May 2016.  This snippet generates the block code normally, then strips out the block IDs
 * for readability when displaying the code to the user.
 *
 * Post-processing the block code in this way allows us to use the same generators for the Android
 * and web versions of the turtle.
 */
Blockly.JavaScript.workspaceToCodeWithId = Blockly.JavaScript.workspaceToCode;

Blockly.JavaScript.workspaceToCode = function(workspace) {
  var code = this.workspaceToCodeWithId(workspace);
  // Strip out block IDs for readability.
  code = goog.string.trimRight(code.replace(/(,\s*)?'block_id_[^']+'\)/g, ')'))
  return code;
};
