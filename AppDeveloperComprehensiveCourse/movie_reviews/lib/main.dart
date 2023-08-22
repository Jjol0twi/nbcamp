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
      home: HomePage(), // 홈페이지 보여주기
    );
  }
}

class HomePage extends StatelessWidget {
  const HomePage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    // 음식 사진 데이터
    List<Map<String, dynamic>> dataList = [
      {
        "category": "탑건: 매버릭",
        "imgUrl": "https://i.ibb.co/sR32PN3/topgun.jpg",
      },
      {
        "category": "마녀2",
        "imgUrl": "https://i.ibb.co/CKMrv91/The-Witch.jpg",
      },
      {
        "category": "범죄도시2",
        "imgUrl": "https://i.ibb.co/2czdVdm/The-Outlaws.jpg",
      },
      {
        "category": "헤어질 결심",
        "imgUrl": "https://i.ibb.co/gM394CV/Decision-to-Leave.jpg",
      },
      {
        "category": "브로커",
        "imgUrl": "https://i.ibb.co/MSy1XNB/broker.jpg",
      },
      {
        "category": "문폴",
        "imgUrl": "https://i.ibb.co/4JYHHtc/Moonfall.jpg",
      },
    ];

    // 화면에 보이는 영역
    return Scaffold(
      appBar: AppBar(
        elevation: 0, // appbar body 구분선 없애기
        centerTitle: false,
        backgroundColor: Colors.white,
        title: Text(
          "Movie Reviews",
          style: TextStyle(
            fontSize: 28,
            color: Colors.black,
            fontWeight: FontWeight.bold,
          ),
        ),
        iconTheme: IconThemeData(
          color: Colors.black,
        ), // appbar icon 색깔 설정
        actions: [
          IconButton(
            onPressed: () {},
            // color: Colors.black,
            icon: Icon(Icons.person_outline),
          )
        ],
      ),
      body: Column(
        children: [
          Padding(
            padding: EdgeInsets.all(8),
            child: TextField(
              decoration: InputDecoration(
                hintText: "영화 제목을 검색해주세요.",
                border: OutlineInputBorder(
                  borderSide: BorderSide(
                    color: Colors.black,
                  ),
                ),
                suffixIcon: IconButton(
                  icon: Icon(Icons.search),
                  onPressed: () {},
                ),
              ),
            ),
          ),
          Divider(
            height: 1,
          ), // 구분선
          Expanded(
            child: ListView.builder(
              itemCount: dataList.length,
              itemBuilder: (context, index) {
                String category = dataList[index]['category']; // 최대한 변수 생성 후 활용
                String imgUrl = dataList[index]['imgUrl'];
                return Card(
                  child: Stack(
                    alignment: Alignment.center,
                    children: [
                      // Text(dataList[index]['category']),
                      Text(category),
                      Image.network(
                        // dataList[index]['imgUrl'],
                        imgUrl,
                        width: double.infinity,
                        height: 200,
                        // fit: BoxFit.fitWidth,
                        fit: BoxFit.cover,
                      ),
                      Container(
                        width: double.infinity,
                        height: 200,
                        color: Colors.black.withOpacity(0.5),
                      ),
                      Text(
                        category,
                        style: TextStyle(
                          color: Colors.white,
                          fontSize: 36,
                        ),
                      ),
                    ],
                  ), // stack은 마지막 위젯이 가장 앞으로
                );
              },
            ),
          ),
        ],
      ),
    );
  }
}
