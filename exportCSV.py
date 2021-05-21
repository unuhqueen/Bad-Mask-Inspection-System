# -*- coding: UTF-8 -*-

import firebase_admin
from firebase_admin import credentials, firestore
import os
import csv
from tkinter import *
from tkinter.messagebox import showinfo

def csvCmd():
    os.environ["GOOGLE_APPLICATION_CREDENTIALS"] = "bad-mask-inspection-system-47270bd0828d.json"

    cred = credentials.ApplicationDefault()
    firebase_admin.initialize_app(cred, {
        'projectId': 'bad-mask-inspection-system',
    })

    db = firestore.client()

    # add your collections manually
    collection_name = 'runsheet'
    dict4json = dict()
    n_documents = 0

    collection = db.collection(collection_name).get()
    for document in collection:
        docdict = document.to_dict()
        dict4json[document.id] = docdict
        n_documents += 1

    od = []
    for value in dict4json.values():
        od.append(sorted(value.items()))

    with open('생산일보.csv', 'w', newline='', encoding='utf-8-sig') as output_file:
        f = csv.writer(output_file)

        header = []
        for i in range(len(od[0])):
            header.append(od[0][i][0])
        f.writerow(header)

        line = []
        for i in range(n_documents):
            for j in range(len(od[0])):
                line.append(od[i][j][1])
            f.writerow(line)
            line = []
    showinfo("파일 저장 성공", "CSV 파일이 다운로드되었습니다.")

def delete_collection(coll_ref, batch_size):
    docs = coll_ref.limit(batch_size).stream()
    deleted = 0

    for doc in docs:
        print(f'Deleting doc {doc.id} => {doc.to_dict()}')
        doc.reference.delete()
        deleted = deleted + 1

    if deleted >= batch_size:
        return delete_collection(coll_ref, batch_size)


def delCmd():
    os.environ["GOOGLE_APPLICATION_CREDENTIALS"] = "bad-mask-inspection-system-47270bd0828d.json"

    cred = credentials.ApplicationDefault()
    firebase_admin.initialize_app(cred, {
        'projectId': 'bad-mask-inspection-system',
    })

    db = firestore.client()

    n_documents = 0
    collection = db.collection('runsheet').get()
    for document in collection:
        n_documents += 1

    delete_collection(db.collection('runsheet'), n_documents)
    showinfo("DB 삭제 성공", "지금까지의 DB 기록이 삭제되었습니다.")

root = Tk()
root.title("CSV 파일 내보내기")
root.geometry("270x190")

csvBtn = Button(root, padx=10, pady=10, text="CSV로 내보내기", command=csvCmd)
csvBtn.pack()
deleteBtn = Button(root, padx=10, pady=10, text="지금까지 DB 기록 삭제하기", command=delCmd)
deleteBtn.pack()

root.mainloop()