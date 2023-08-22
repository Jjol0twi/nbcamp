import 'package:flutter/material.dart';
import 'layout_main.dart';
import 'layout_chart.dart';
import 'layout_library.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  int focusedLayout = 1;
  final PageController pageViewController = PageController(
    initialPage: 1,
  );
  List<String> appbarText = ["라이브러리", "", "차트"];
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Shazam',
      // debugShowCheckedModeBanner: false,
      home: Scaffold(
        appBar: AppBar(
          leadingWidth: 80,
          backgroundColor: focusedLayout == 1 ? Colors.blue[300] : Colors.white,
          elevation: 0.0,
          centerTitle: true,
          leading: focusedLayout == 1
              ? Container(
                  margin: EdgeInsets.only(top: 10),
                  child: Column(
                    children: [
                      Icon(Icons.person),
                      Text(
                        "라이브러리",
                        style: TextStyle(color: Colors.white),
                      ),
                    ],
                  ),
                )
              : focusedLayout == 0
                  ? Icon(
                      Icons.settings,
                      color: Colors.black,
                    )
                  : null,
          actions: [
            focusedLayout == 1
                ? Padding(
                    padding: const EdgeInsets.fromLTRB(30, 0, 20, 0),
                    child: Container(
                      margin: EdgeInsets.only(top: 10),
                      child: Column(
                        children: [
                          Icon(Icons.show_chart),
                          Text(
                            "차트",
                            style: TextStyle(color: Colors.white),
                          ),
                        ],
                      ),
                    ),
                  )
                : focusedLayout == 0
                    ? Padding(
                        padding: EdgeInsets.only(right: 80),
                      )
                    : Text(""),
          ],
          title: Column(
            children: [
              Text(
                appbarText[focusedLayout],
                style: TextStyle(
                  color: Colors.black,
                  fontWeight: FontWeight.bold,
                ),
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Container(
                    width: 9,
                    height: 9,
                    decoration: BoxDecoration(
                      shape: BoxShape.circle,
                      color:
                          focusedLayout == 0 ? Colors.blue : Colors.transparent,
                      border: Border.all(
                          color:
                              focusedLayout == 1 ? Colors.white : Colors.blue),
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.fromLTRB(9, 0, 9, 0),
                    child: Container(
                      width: 10,
                      height: 10,
                      decoration: BoxDecoration(
                        shape: BoxShape.circle,
                        color: focusedLayout == 1
                            ? Colors.white
                            : Colors.transparent,
                        border: Border.all(
                            color: focusedLayout == 1
                                ? Colors.white
                                : Colors.blue),
                      ),
                    ),
                  ),
                  Container(
                    width: 10,
                    height: 10,
                    decoration: BoxDecoration(
                      shape: BoxShape.circle,
                      color:
                          focusedLayout == 2 ? Colors.blue : Colors.transparent,
                      border: Border.all(
                          color:
                              focusedLayout == 1 ? Colors.white : Colors.blue),
                    ),
                  ),
                ],
              ),
            ],
          ),
        ),
        body: Center(
          child: SafeArea(
            child: PageView(
              onPageChanged: (value) {
                setState(() {
                  focusedLayout = value;
                });
              },
              scrollDirection: Axis.horizontal,
              controller: pageViewController,
              children: [
                LayoutLibrary(),
                LayoutMain(),
                LayoutChart(),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
