import 'package:flutter/material.dart';

class LayoutLibrary extends StatelessWidget {
  const LayoutLibrary({super.key});

  @override
  Widget build(BuildContext context) {
    List<Map<String, dynamic>> libraryListText = [
      {
        'title': "Shazam",
        'icon': ImageIcon(
          NetworkImage("https://i.ibb.co/hxNbZ8p/shazam.png"),
          size: 18,
        ),
      },
      {
        'title': "아티스트",
        'icon': Icon(Icons.person_rounded),
      },
      {
        'title': "회원님을 위한 재생 목록",
        'icon': Icon(Icons.music_note),
      },
    ];
    const songs = [
      {
        'imageUrl': 'https://i.ibb.co/MRSqtP8/autumnnight.jpg',
        'title': '가을밤에 든 생각',
        'artist': '잔나비',
      },
      {
        'imageUrl': 'https://i.ibb.co/MRSqtP8/autumnnight.jpg',
        'title': '가을밤에 든 생각',
        'artist': '잔나비',
      },
      {
        'imageUrl': 'https://i.ibb.co/MRSqtP8/autumnnight.jpg',
        'title': '가을밤에 든 생각',
        'artist': '잔나비',
      },
      {
        'imageUrl': 'https://i.ibb.co/MRSqtP8/autumnnight.jpg',
        'title': '가을밤에 든 생각',
        'artist': '잔나비',
      },
      {
        'imageUrl': 'https://i.ibb.co/MRSqtP8/autumnnight.jpg',
        'title': '가을밤에 든 생각',
        'artist': '잔나비',
      },
      {
        'imageUrl': 'https://i.ibb.co/MRSqtP8/autumnnight.jpg',
        'title': '가을밤에 든 생각',
        'artist': '잔나비',
      },
    ];
    // return Placeholder();
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        ListView.separated(
            shrinkWrap: true,
            itemBuilder: (context, index) {
              return Padding(
                padding: const EdgeInsets.all(8.0),
                child: Row(
                  children: [
                    libraryListText[index]['icon'],
                    SizedBox(width: 8),
                    Text(
                      libraryListText[index]['title'],
                      style: TextStyle(
                        fontSize: 18,
                        fontWeight: FontWeight.w600,
                      ),
                    ),
                  ],
                ),
              );
            },
            separatorBuilder: (context, index) {
              return Divider();
            },
            itemCount: libraryListText.length),
        Padding(
          padding: const EdgeInsets.all(15),
          child: Text(
            "최근 Shazam",
            style: TextStyle(
              fontSize: 18,
              fontWeight: FontWeight.w600,
            ),
          ),
        ),
        Expanded(
          child: GridView.builder(
            gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
              crossAxisCount: 2,
              childAspectRatio: 3 / 5,
            ),
            itemBuilder: (context, index) {
              var song = songs[index];
              String imageUrl = song['imageUrl']!;
              String title = song['title']!;
              String artist = song['artist']!;

              index % 2 == 0; // 왼쪽, 1이면 오른쪽

              return Container(
                margin: EdgeInsets.only(
                  left: 4,
                  right: 4,
                  top: 4,
                  bottom: 4,
                ),
                decoration: BoxDecoration(
                  color: Colors.white,
                  borderRadius: BorderRadius.all(
                    Radius.circular(8),
                  ),
                  boxShadow: [
                    BoxShadow(
                      color: Colors.grey.withOpacity(0.5),
                      blurRadius: 1,
                      spreadRadius: 1,
                    ),
                  ],
                ),
                child: Column(
                  children: [
                    ClipRRect(
                      borderRadius: BorderRadius.only(
                        topLeft: Radius.circular(8),
                        topRight: Radius.circular(8),
                      ),
                      child: Image.network(
                        imageUrl,
                        fit: BoxFit.cover,
                        height: MediaQuery.of(context).size.width *
                            0.5 *
                            5 /
                            3 *
                            0.55,
                      ),
                    ),
                    Expanded(
                      child: Container(
                        padding: const EdgeInsets.all(8),
                        width: double.infinity,
                        child: Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: [
                            Text(
                              title,
                              style: TextStyle(
                                fontSize: 18,
                                fontWeight: FontWeight.bold,
                              ),
                            ),
                            Text(
                              artist,
                              style: TextStyle(
                                fontSize: 14,
                                color: Colors.grey[600],
                              ),
                            ),
                            Spacer(),
                            Image.network(
                              "https://i.ibb.co/KG9m5QS/applemusic.png",
                              width: 60,
                            ),
                            SizedBox(height: 5),
                          ],
                        ),
                      ),
                    )
                  ],
                ),
              );
            },
            itemCount: songs.length,
          ),
        ),
      ],
    );
  }
}
