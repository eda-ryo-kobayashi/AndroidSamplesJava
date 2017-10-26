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
- dev : 開発環境。テストサーバーや開発版で行う処理など。
- pro : 本番環境。

フレーバーにはdimensionというパラメータがあり、

異なるdimensionのフレーバーは組み合わせることができる

例) 3つのdimensionがあるとする

env, network, database

各dimensionそれぞれに以下のフレーバーがあるとする

- env : envDev, envPro
- network : apiReal, apiFake
- database : dbReal, dbFake

以上のフレーバーの組み合わせは全部で

default(2) x network(2) x database(2)

の8通りある。

実際には不要な組み合わせがあるので、build.gradleに設定を書く

### テスト系
- androidTest : Androidに依存するクラスのテスト
- test : Androidに依存しないクラスのテスト