# ビルドタイプとプロダクトフレーバー

## ファイル構成
現在プロジェクトのソースの構成

### メイン
- main : 全てのビルドタイプ・プロダクトフレーバーで共通するソース
### ビルドタイプ
- debug : デバッグ。ここには主にログやテスト画面を入れる。
- release : リリース。ここにはリリースの処理が入る。
### プロダクトフレーバー
プロダクトフレーバーは自由に設定できる。
以下は例
- mock : サンドボックス。サーバーAPIの値を固定で返すなど、ネットワークがなくても動作するような作り。
- dev : 開発環境。サーバーの向先や開発版で行う処理など。
- pro : 本番環境。

フレーバーにはdimensionというパラメータがあり、

異なるdimensionのフレーバーは組み合わせることができる

例) 3つのdimensionがあるとする

default, network, database

それぞれ以下のフレーバーがあるとする

- default : mock, dev, pro
- network : realapi, fakeapi
- database : realdb, fakedb

以上のフレーバーの組み合わせは全部で

default(3) x network(2) x database(2)

の12通りある。

### テスト系
- androidTest : Androidに依存するクラスのテスト
- test : Androidに依存しないクラスのテスト