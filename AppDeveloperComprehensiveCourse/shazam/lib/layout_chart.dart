import 'package:flutter/material.dart';

class LayoutChart extends StatelessWidget {
  const LayoutChart({super.key});

  @override
  Widget build(BuildContext context) {
    const chartData = {
      '대한민국': [
        {
          'imageUrl': 'https://i.ibb.co/xf2HpfG/dynamite.jpg',
          'name': 'Dynamite',
          'artist': 'BTS',
        },
        {
          'imageUrl': 'https://i.ibb.co/xf2HpfG/dynamite.jpg',
          'name': 'Dynamite',
          'artist': 'BTS',
        },
        {
          'imageUrl': 'https://i.ibb.co/xf2HpfG/dynamite.jpg',
          'name': 'Dynamite',
          'artist': 'BTS',
        },
      ],
      '글로벌': [
        {
          'imageUrl': 'https://i.ibb.co/xf2HpfG/dynamite.jpg',
          'name': 'Dynamite',
          'artist': 'BTS',
        },
        {
          'imageUrl': 'https://i.ibb.co/xf2HpfG/dynamite.jpg',
          'name': 'Dynamite',
          'artist': 'BTS',
        },
        {
          'imageUrl': 'https://i.ibb.co/xf2HpfG/dynamite.jpg',
          'name': 'Dynamite',
          'artist': 'BTS',
        },
      ],
      '뉴욕': [
        {
          'imageUrl': 'https://i.ibb.co/xf2HpfG/dynamite.jpg',
          'name': 'Dynamite',
          'artist': 'BTS',
        },
        {
          'imageUrl': 'https://i.ibb.co/xf2HpfG/dynamite.jpg',
          'name': 'Dynamite',
          'artist': 'BTS',
        },
        {
          'imageUrl': 'https://i.ibb.co/xf2HpfG/dynamite.jpg',
          'name': 'Dynamite',
          'artist': 'BTS',
        },
      ],
    };
    print("1111${chartData['대한민국']}");
    // return Placeholder();
    return Column(
      children: [
        Container(
          color: Colors.purple[900],
          alignment: Alignment.center,
          width: double.infinity,
          height: MediaQuery.of(context).size.width * 0.45,
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Container(
                width: MediaQuery.of(context).size.width * 0.8,
                child: ElevatedButton(
                  style: ButtonStyle(
                    backgroundColor: MaterialStateProperty.all(Colors.white),
                  ),
                  onPressed: () {},
                  child: Text(
                    "국가 및 도시별 차트",
                    style: TextStyle(
                      color: Colors.purple[900],
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                ),
              ),
              Text(
                "전 세계",
                style: TextStyle(
                  color: Colors.white,
                  fontWeight: FontWeight.bold,
                ),
              ),
            ],
          ),
        ),
        Container(
          width: double.infinity,
          height: 8,
          color: Colors.grey[400],
        ),
        Flexible(
          flex: 1,
          child: ListView.separated(
            itemCount: chartData.length,
            itemBuilder: ((context, index) {
              String chartName = chartData.keys.elementAt(index);
              List<Map<String, String>>? chartItems = chartData[chartName];
              return Padding(
                padding: const EdgeInsets.all(8.0),
                child: Column(
                  children: [
                    Row(
                      children: [
                        Text("$chartName 차트"),
                        Spacer(),
                        Text(
                          "모두 보기",
                          style: TextStyle(color: Colors.blue),
                        ),
                      ],
                    ),
                    SizedBox(
                      height: MediaQuery.of(context).size.width * 0.4,
                      child: ListView.builder(
                        itemCount: chartItems![index].length,
                        scrollDirection: Axis.horizontal,
                        itemBuilder: ((context, index) {
                          String imageUrl = chartItems[index]['imageUrl']!;
                          String name = chartItems[index]['name']!;
                          String artist = chartItems[index]['artist']!;
                          return Padding(
                            padding: const EdgeInsets.fromLTRB(8, 8, 8, 0),
                            child: Column(
                              crossAxisAlignment: CrossAxisAlignment.start,
                              children: [
                                Image.network(
                                  imageUrl,
                                  width:
                                      MediaQuery.of(context).size.width * 0.29,
                                ),
                                Text(
                                  name,
                                  style: TextStyle(fontWeight: FontWeight.bold),
                                ),
                                Text(artist),
                              ],
                            ),
                          );
                        }),
                      ),
                    ),
                  ],
                ),
              );
            }),
            separatorBuilder: (context, int index) {
              return Container(
                width: double.infinity,
                height: 8,
                color: Colors.grey[400],
              );
            },
          ),
        ),
      ],
    );
  }
}
