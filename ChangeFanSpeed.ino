int x;
int FAN = 3;
void setup()
{
    Serial.begin(9600);
}
void loop()
{

    if (Serial.available() > 0)
    {
        x = Serial.read();
    }
    if (x == '1')
    {
        analogWrite(FAN, 130);
    }
    else if (x == '2')
    {
        analogWrite(FAN, 160);
    }
    else if (x == '3')
    {
        analogWrite(FAN, 190);
    }
    else if (x == '4')
    {
        analogWrite(FAN, 220);
    }
    else if (x == '5')
    {
        analogWrite(FAN, 255);
    }
    else if (x == '0')
    {
        analogWrite(FAN, 0);
    }
}