import cv2
from PIL import Image
import numpy as np
from PIL import ImageEnhance
import csv
from skimage.filters import threshold_sauvola
from matplotlib import pyplot as plt
import os
import sys

class ProcessImage:
    """
    Image Processing
    """
    def __init__(self,img):
        self.img = img
        self.img_SE = []
        self.img_SE_HSV = []
        self.img_SE_HSV_H = []
        self.BIN_SE_HSV_img_H = []

        #self.threshold = 25

        self.particle_list = []
        self.areas = []
        self.area_image = 0
        self.dias = []
        self.count = 0
        self.range_size = []

        self.total_area = 0
        self.mean_area = 0
        self.std_area = 0
        self.mean_dia = 0
        self.std_dia = 0
        self.ratio_area = 0

    def sharpness_ImageEnhance(self,file):
        '''
        Image enhancement sharpening
        :param file: The path of img file
        :return: Image after enhancement sharpening
        '''
        img_origin = Image.open(file)
        enh_sha = ImageEnhance.Sharpness(img_origin)
        sharpness = 10
        image_sharped = enh_sha.enhance(sharpness)
        image_sharped = cv2.cvtColor(np.array(image_sharped), cv2.COLOR_RGB2BGR)
        return image_sharped

    def process(self,threshold = 41):
        '''
        binarizer image
        '''
        
        ##self.img_SE = self.sharpness_ImageEnhance(self.img)
        #self.img_SE = cv2.resize(self.img_SE , (512, 512))
        self.img_SE = cv2.imread(self.img)
        self.img_SE_HSV = cv2.cvtColor(self.img_SE, cv2.COLOR_BGR2HSV)
       
        self.img_SE_HSV_H = self.img_SE_HSV[:, :, 0]
        
        #print ( '[INFO] saving (1) H channel...' )
        #cv2.imwrite('res/1_img_HSV_H.tiff', self.img_SE_HSV_H)
        
        ##ret, self.BIN_SE_HSV_img_H = cv2.threshold(self.img_SE_HSV_H, threshold, 255, cv2.THRESH_BINARY)
        
        # =============================================================================
        # DYNAMIC RANGE        
        # =============================================================================
        his = plt.hist(self.img_SE_HSV_H[:,:].ravel(),256,[0,256])
        
        # V0, v1 limites de histogramme, où non null
        i = 0
        V0 = 0
        V1 = 0
        Vmin=0
        Vmax = 255

        #calcul de V1
        while his[0][i] != 0:
            i+=1
        V1 = i  
        d = (Vmin*V1-Vmax*V0)/(V1-V0)
        g = (Vmax-Vmin)/(V1-V0)
        self.img_SE_HSV_H[:,:] = np.multiply(g,self.img_SE_HSV_H[:,:])+d  
        #print ( '[INFO] (2) saving upgraded range...' )
        #cv2.imwrite('res/2_DYNAMICRANGE_img_SE_HSV_img_H.tiff', self.img_SE_HSV_H)
        
        
        # =============================================================================
        # BINARISATION        
        # =============================================================================
        windows_size = 41
        thresh = threshold_sauvola(self.img_SE_HSV_H,windows_size)
        self.BIN_SE_HSV_img_H = 255 - 255 * (self.img_SE_HSV_H>thresh)
        #print ( '[INFO] (3) saving binary image...' )
        #cv2.imwrite('res/3_BIN_HSV_H.tiff', self.BIN_SE_HSV_img_H)
        
        
        # ------------------------------------------------------------
        # Calcul contour
        # ------------------------------------------------------------
        #self.BIN_SE_HSV_img_H = np.uint8(self.BIN_SE_HSV_img_H)
        #self.BIN_SE_HSV_img_H = cv2.cvtColor(self.BIN_SE_HSV_img_H, cv2.COLOR_BGR2GRAY)
        
        self.BIN_SE_HSV_img_H = self.BIN_SE_HSV_img_H.astype(np.uint8)
        listContours, hierarchy = cv2.findContours(self.BIN_SE_HSV_img_H, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_NONE)
        
        # =============================================================================
        # Binarisation des éléments
        # =============================================================================
        
        #output = cv2.cvtColor(output, cv2.COLOR_GRAY2BGR)
        c = 0
        while c  != len(listContours)-1:                  # a optimiser car on ne prend pas tous les contours donc pas utilise de prendre tous les contours 
            
            area = [cv2.contourArea(listContours[c])]
            if(area[0] >= 1889.5 ):
                if area[0] >= 779 and area[0] < 1889.5:
                    window_size= 41
                    #print("windows size = ",window_size)
                elif area[0] >= 1889.5 and area[0] < 3000:
                    window_size= 47
                    #print("windows size = ",window_size)
                elif area[0] >= 3000 and area[0] < 4110.5:
                    window_size= 51
                    #print("windows size = ",window_size)
                elif area[0] >= 4110.5 and area[0] < 5221:
                    window_size= 57
                    #print("windows size = ",window_size)
                elif area[0] >=5221 and area[0] <7442:
                    window_size= 61
                    #print("windows size = ",window_size)        
                elif area[0] >=7442 and area[0] <9663:
                    window_size = 73
                    #print("windows size = ",window_size)            
                elif area[0] >= 9663 and area[0] < 11884:
                    window_size= 81
                    #print("windows size = ",window_size)            
                elif area[0] >= 11884 and area[0] < 14105:
                    window_size = 91
                    #print("windows size = ",window_size)            
                elif area[0] >= 14105 and area[0] < 16326:
                    window_size= 91
                    #print("windows size = ",window_size)        
                elif area[0] >= 16326 and area[0] < 18547:
                    window_size= 101
                    #print("windows size = ",window_size)            
                elif area[0] >= 18547:
                    window_size= 111
                    #print("windows size = ",window_size)            
                
                cnt = listContours[c]
                x, y, w, h = cv2.boundingRect(cnt)          
                #cv2.rectangle(self.BIN_SE_HSV_img_H, (x, y), (x+w, y+h), (255, 255, 255), 2)
                
                # récupération des elements repondant à la condition dans image HSV
                img_mat =  np.copy(self.img_SE_HSV_H[y:y+h, x:x+w])
                
                
                # binarisation de l'élément 
                thresh_sauvola = threshold_sauvola(img_mat, window_size=window_size)
                img_mat = 255-255 * (img_mat > thresh_sauvola)
                
                
                # replace l'element dans l'image final
                self.BIN_SE_HSV_img_H[y:y+h, x:x+w] = img_mat 
                
            c +=1 
        #print ( '[INFO] (4) saving corrected bin image ...' )
        #cv2.imwrite('res/4_correctionBin.tiff', self.BIN_SE_HSV_img_H)
       
       
        self.BIN_SE_HSV_img_H = cv2.medianBlur(self.BIN_SE_HSV_img_H,3)
        #print ( '[INFO] (5) saving blured image...' )
        #cv2.imwrite('res/5_blur_BIN_HSV_H.tiff', self.BIN_SE_HSV_img_H)
        
        #cv2.imshow("Bin", self.BIN_SE_HSV_img_H)
        self.BIN_SE_HSV_img_H = 255 - self.BIN_SE_HSV_img_H
        kernel = np.ones((3, 3), np.uint8)
        #self.BIN_SE_HSV_img_H = cv2.morphologyEx(self.BIN_SE_HSV_img_H, cv2.MORPH_OPEN, kernel,iterations=1)
        self.BIN_SE_HSV_img_H = cv2.morphologyEx(self.BIN_SE_HSV_img_H, cv2.MORPH_CLOSE, kernel, iterations=1)
        
        #self.BIN_SE_HSV_img_H = cv2.dilate(self.BIN_SE_HSV_img_H, kernel, iterations=1)
        #print ( '[INFO] (6) saving new binary image...' )
        #cv2.imwrite("res/6_Bin2.tiff",self.BIN_SE_HSV_img_H)
        # #kernel = cv2.getStructuringElement(cv2.MORPH_RECT, (3, 3))
        #kernel = np.ones((3, 3), np.uint8)
        self.BIN_SE_HSV_img_H = cv2.morphologyEx(self.BIN_SE_HSV_img_H, cv2.MORPH_CLOSE, kernel)
        
        self.sure_bg = cv2.dilate(self.BIN_SE_HSV_img_H,kernel,iterations=1)
        #cv2.imwrite('res/sur_bg.tiff', self.sure_bg)
        #cv2.imshow("ddd",self.sure_bg)
        self.dist_transform = cv2.distanceTransform(self.BIN_SE_HSV_img_H, cv2.DIST_L2, 5)
        #cv2.imwrite('res/dist_transform.tiff', self.dist_transform)
        #cv2.imshow("aaa",self.dist_transform)
        self.PARTICLES = 255 - self.sure_bg
        #cv2.imwrite('res/particles.tiff', self.PARTICLES)


    def evaluate(self,type):
        '''
        Evaluate the result of binarization
        :param type: type of evaluate, 'FN' or 'FP'

        '''
        if type == 'FN':
            mask = self.BIN_SE_HSV_img_H // 255
        elif type == 'FP':
            mask = 1 - self.BIN_SE_HSV_img_H // 255
        else:
            mask = 0
        (B, G, R) = cv2.split(self.img_SE)
        img_evaluate = cv2.merge([B * mask, G * mask, R * mask])
        #cv2.imshow('img_'+ type, img_evaluate)
        #cv2.imwrite('./evaluate/' + type+ ' with threshold of'+str(self.threshold)+'.png',img_evaluate)


    def calculateFeatures(self):
        '''
        Calculate the particle features in the image, including total area, average area, standard deviation of area,
        average diameter, standard deviation of diameter, and the proportion of particles in the total image
        '''
        self.total_area = np.sum(self.areas)
        self.mean_area = np.mean(self.areas)
        self.std_area = np.std(self.areas)
        self.mean_dia = np.mean(self.dias)
        self.std_dia = np.std(self.dias)
        self.ratio_area = self.total_area / self.area_image


    def export(self,file_export):
        '''
        Export the information of the particles in the figure to a csv file
        '''
        with open(file_export, 'w', newline='') as f:
            writer = csv.writer(f)
            writer.writerow(['Total Area', 'Average Area', 'Std of Area', 'Average Diameter', 'Std of Diameter','Ratio of particles'])
            writer.writerow([self.total_area,self.mean_area,self.std_area,self.mean_dia,self.std_dia,self.ratio_area])
            writer.writerow(['No.', 'Area', 'Diameter', 'Location'])
            for raw_data in self.particle_list:
                writer.writerow(raw_data)


    def extract(self, data_name, edge_name):

        import cv2
        import numpy as np

        imgOrig = self.img_SE

        from skimage.measure import label, regionprops
        from math import pi

        # connected components
        # labeling 
        label_image = label(self.PARTICLES)

        # https://scikit-image.org/docs/stable/api/skimage.measure.html#skimage.measure.regionprops
        from skimage.measure import regionprops_table
        props = regionprops_table(label_image, 
                                  properties=('area',
                                              'bbox',
                                              'centroid',
                                              'orientation',
                                              'axis_major_length',
                                              'axis_minor_length',
                                              'equivalent_diameter_area')
                                  )
        import pandas as pd
        results = pd.DataFrame(props)
        results.set_axis(["area", "ystart", "xstart", "yend", "xend", "ycen", "xcen", "orientation", "axis1", "axis2", "eqdiam"], axis='columns')
        results.to_csv(data_name, index=False)

        # edge detection on mask
        from skimage import feature

        imgEdges = feature.canny(self.PARTICLES, sigma=1)
        #print ( '[INFO] (9) saving edges image...' )
        #cv2.imwrite( 'res/9-edges4.png', imgEdges *255 )

        imgOrig[:,:,0] = (imgEdges * 255) + imgOrig[:,:,0]
        imgOrig[:,:,1] = (imgEdges * 255) + imgOrig[:,:,1]
        imgOrig[:,:,2] = (imgEdges * 255) + imgOrig[:,:,2]
        np.where(imgOrig>255, 255, imgOrig)

        #print ( '[INFO] (10) saving final image...' )
        cv2.imwrite( edge_name, imgOrig )


if __name__ == "__main__":
    dir = os.getcwd().replace("\\", "/")
    filename = sys.argv[1]
    pro = ProcessImage(dir + "/scriptsImages/" + filename)

    pro.process()
    pro.extract(dir + '/scriptsImages/res/out_data.csv', dir + '/scriptsImages/res/out_edges.png')




