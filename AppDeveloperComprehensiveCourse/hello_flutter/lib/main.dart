import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Scaffold(
        appBar: AppBar(
          centerTitle: true,
          title: Text(
            "Hello flutter",
            style: TextStyle(fontSize: 28),
          ), // login title text
        ),
        body: SingleChildScrollView(
          child: Padding(
            padding: const EdgeInsets.all(16),
            child: Column(
              children: [
                Padding(
                  padding: EdgeInsets.all(32),
                  child: Image.network(
                    "https://i.ibb.co/nngK6j3/startup.png",
                    width: 81,
                  ),
                ),
                TextField(
                  decoration: InputDecoration(
                    labelText: "이메일", // textfield hint
                  ),
                ), // email textfield
                TextField(
                  obscureText: true, // hide text
                  decoration: InputDecoration(
                    labelText: "비밀번호", // textfield hint
                  ),
                ), // password textfield
                Container(
                  margin: EdgeInsets.only(top: 24),
                  width: double.infinity,
                  child: ElevatedButton(
                    onPressed: () {},
                    child: Text("로그인"),
                  ),
                ), // Login button
              ],
            ),
          ),
        ), // main body layout
      ),
    );
  }
}
