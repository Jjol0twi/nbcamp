import 'package:flutter/material.dart';

class LayoutMain extends StatelessWidget {
  const LayoutMain({super.key});

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
        gradient: LinearGradient(
          begin: Alignment.topCenter,
          end: Alignment.bottomCenter,
          colors: [Colors.blue[300]!, Colors.blue[900]!],
        ),
      ),
      child: Column(
        children: [
          SizedBox(height: MediaQuery.of(context).size.height * 0.1),
          Text(
            "Shazam하려면 탭하세요",
            style: TextStyle(
              color: Colors.white,
              fontSize: 24,
              fontWeight: FontWeight.bold,
            ),
          ),
          SizedBox(
            height: MediaQuery.of(context).size.height * 0.06,
          ),
          Container(
            alignment: Alignment.center,
            width: 200,
            height: 200,
            decoration: BoxDecoration(
              color: Colors.blue[300],
              shape: BoxShape.circle,
            ),
            child: Image.network(
              "https://i.ibb.co/hxNbZ8p/shazam.png",
              color: Colors.white,
              width: 130,
              height: 130,
            ),
          ),
          SizedBox(
            height: MediaQuery.of(context).size.height * 0.12,
          ),
          Container(
            width: 50,
            height: 50,
            alignment: Alignment.center,
            decoration: BoxDecoration(
              color: Colors.blue[300],
              shape: BoxShape.circle,
            ),
            child: Icon(
              Icons.search,
              color: Colors.white,
              size: 30,
            ),
          )
        ],
      ),
    );
  }
}
