import ddddocr
import sys



def func(a):
    ocr = ddddocr.DdddOcr()
    with open(a, 'rb') as f:

        res = ocr.classification(f.read())
        return res
        print(res)

if __name__ == '__main__':
    var = sys.argv[1]
    print(func(var))

