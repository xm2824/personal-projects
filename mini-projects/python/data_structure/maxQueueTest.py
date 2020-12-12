import unittest
from GAD.Datenstrukturen.MaxQueue import  MaxQueue

class t(unittest.TestCase):
    queue = MaxQueue(10)
    queue.addAll([
        [9, 'PGdP'],
        [8, 'GAD'],
        [7, 'EIST'],
        [6, 'GAd'],
        [8,'ERA']
    ])


    def test_Height(self):
        self.assertEqual(2,t.queue.heapHeight() )
    def test_str(self):
        print(t.queue)
    def test_priority(self):
        for _ in range(t.queue.heapSize):
            print(t.queue.extractHighestPriorityEvent())
    def test_search(self):
        print( t.queue.searchEventOfPriority(8))
    def test_filter(self):
        print(t.queue.filterEvents(7))


if __name__ == '__main__':
    unittest.main()
