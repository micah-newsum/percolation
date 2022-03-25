How to check if site is full without checking if it's connected to virtual top site?
- Add logic within open method that sets a flag on site indicating that it is connected to the top.
    - Change boolean sites to byte sites.
        - lowest order bit will indicate if site is open.
        - second lowest order bit will indicate if site is open and connected to top.
- Percolation will still check if site is connected to virutal top.
- isFull will check site bits for openness and connection to top, rather than simply it being connected to virtual top site.