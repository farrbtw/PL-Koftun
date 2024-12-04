import requests
import json
import tkinter as tk
from tkinter import messagebox

def rep_inf(rep_name):
    url = f'https://api.github.com/repos/{rep_name}'
    response = requests.get(url)

    if response.status_code == 200:
        rep_data = response.json()
        rep_info = {
            'company': None,  
            'created_at': rep_data.get('created_at'),
            'email': None,  
            'id': rep_data.get('id'),
            'name': rep_data.get('name'),
            'url': rep_data.get('html_url')
        }
        return rep_info
    else:
        messagebox.showerror('Ошибка', 'Репозиторий не найден')

def button_click():
    rep_name = entry.get()
    if rep_name:
        info = rep_inf(rep_name)
        if info:
            with open(f'{rep_name.replace("/", "_")}_info.json', 'w') as json_file:
                json.dump(info, json_file, indent=4)
            messagebox.showinfo("Отлично", f"Информация о репозитории сохранена в файл '{rep_name.replace('/', '_')}_info.json'")
    else:
        messagebox.showwarning("Предупреждение", "Пожалуйста, введите имя репозитория.")

root = tk.Tk()
root.title('Rep Info')
tk.Label(root, text='Введите имя репозитория (username/repository):').pack(pady=5)
entry = tk.Entry(root, width=50)
entry.pack(pady=5)
button = tk.Button(root, text="Получить информацию", command=button_click)
button.pack(pady=20)
root.mainloop()
