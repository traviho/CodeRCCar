#include <SoftwareSerial.h>
SoftwareSerial Genotronex(0, 1); // TX, RX
#define MAX_BUFFER 4

int* buffer;
int methodNumber = 0;

const int in1 = 5;
const int in2 = 6;
const int in3 = 10;
const int in4 = 11;
const int pingPin = 7;
int forward_speed = 120;
int reverse_speed = 100;
int turn_speed = 220;
int move_state = 0;
long inches = 999;
long duration = 0;

void setup(){
  Genotronex.begin(9600);
  Serial.begin(9600);
  Serial.println("start reading");
  pinMode(in1, OUTPUT);
  pinMode(in2, OUTPUT);
  pinMode(in3, OUTPUT);
  pinMode(in4, OUTPUT);
  stop_car();
  buffer = new int[MAX_BUFFER];
}
void loop() {
  while (!Genotronex.available()) {
    return;
  }
  methodNumber = Genotronex.read() - '0';

  Serial.println(methodNumber);
  if (methodNumber == 0){
    stop_car();
  } else if (methodNumber == 1){
    moveForward();
  } else if (methodNumber == 2){
    moveBackward();
  } else if (methodNumber == 3){
    turnLeft();
  } else if (methodNumber == 4){
    turnRight();
  } else if (methodNumber == 5){
    moveWithDetectionRight();
  }
}

void turnRight(){
  analogWrite(in1, turn_speed);
  analogWrite(in2, 0);
  analogWrite(in3, forward_speed);
  analogWrite(in4, 0);
  delay(1000);
  digitalWrite(in1, LOW);
  digitalWrite(in2, LOW);
  digitalWrite(in3, LOW);
  digitalWrite(in4, LOW);
}

void turnLeft(){
  analogWrite(in1, 0);
  analogWrite(in2, turn_speed);
  analogWrite(in3, forward_speed);
  analogWrite(in4, 0);
  delay(1000);
  digitalWrite(in1, LOW);
  digitalWrite(in2, LOW);
  digitalWrite(in3, LOW);
  digitalWrite(in4, LOW);
}

void moveForward(){
  digitalWrite(in1, LOW);
  digitalWrite(in2, LOW);
  analogWrite(in3, forward_speed);
  analogWrite(in4, 0);
  delay(1000);
  digitalWrite(in3, LOW);
  digitalWrite(in4, forward_speed);
  delay(100);
  digitalWrite(in3, LOW);
  digitalWrite(in4, LOW);
}

void moveBackward(){
  digitalWrite(in1, LOW);
  digitalWrite(in2, LOW);
  analogWrite(in3, 0);
  analogWrite(in4, reverse_speed);
  delay(1000);
  digitalWrite(in3, LOW);
  digitalWrite(in4, LOW);
}

void moveWithDetectionRight(){
  int count = 0;
  int i = 0;
  while(count < 40){
    for (i = 0;i < 3;i++){
      pinMode(pingPin, OUTPUT);
      digitalWrite(pingPin, LOW);
      delayMicroseconds(2);
      digitalWrite(pingPin, HIGH);
      delayMicroseconds(5);
      digitalWrite(pingPin, LOW);
      pinMode(pingPin, INPUT);
      duration = pulseIn(pingPin, HIGH);
      inches = microsecondsToInches(duration);
      Serial.println(inches);
      if (inches != 0){
        break;
      }
    }
    if (i == 3){
      break;
    }
    if (inches < 50 && move_state != 5 && inches != 0){
      move_state = 5;
      digitalWrite(in1, LOW);
      digitalWrite(in2, LOW);
      digitalWrite(in3, LOW);
      digitalWrite(in4, LOW);
      delay(1000);
      analogWrite(in3, 0);
      analogWrite(in4, reverse_speed * .6);
      analogWrite(in1, 0);
      analogWrite(in2, 0);
    } else if (inches > 40 && move_state != 1){
      move_state = 1;
      digitalWrite(in1, LOW);
      digitalWrite(in2, LOW);
      digitalWrite(in3, LOW);
      digitalWrite(in4, LOW);
      delay(1000);
      analogWrite(in3, forward_speed * .6);
      analogWrite(in4, 0);
    } else if (move_state == 0){
      digitalWrite(in1, LOW);
      digitalWrite(in2, LOW);
      analogWrite(in3, forward_speed * .6);
      analogWrite(in4, 0);
      move_state = 1;
    }
    delay(100);
    count++;
  }
  stop_car();
}

void ping(){
  pinMode(pingPin, OUTPUT);
  digitalWrite(pingPin, LOW);
  delayMicroseconds(2);
  digitalWrite(pingPin, HIGH);
  delayMicroseconds(5);
  digitalWrite(pingPin, LOW);
  pinMode(pingPin, INPUT);
  duration = pulseIn(pingPin, HIGH);
  inches = microsecondsToInches(duration);
  delay(500);
}

void stop_car(){
  digitalWrite(in1, LOW);
  digitalWrite(in2, LOW);
  digitalWrite(in3, LOW);
  digitalWrite(in4, LOW);
  move_state = 0;
}

long microsecondsToInches(long microseconds) {
  return microseconds / 74 / 2;
}

